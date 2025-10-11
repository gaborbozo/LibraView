import { provideServerRendering } from '@angular/ssr'
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http'
import {
  ApplicationConfig,
  inject,
  mergeApplicationConfig,
  provideAppInitializer,
  provideZoneChangeDetection
} from '@angular/core'
import { provideClientHydration } from '@angular/platform-browser'
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async'
import { provideRouter, Routes } from '@angular/router'
import { ResponseInterceptor } from './core/interceptors/response.interceptor'
import { LibraAuthenticationGuard } from './core/services/libra-authentication-guard'
import { HomeComponent } from './features/home/home.component'
import { LoginComponent } from './features/login/login.component'
import { SettingsComponent } from './features/settings/settings.component'
import { DefaultService as LibraMasterApi } from '../generated/api/master'
import { DefaultService as TmdbApi } from '../generated/api/tmdb'
import { DefaultService as LibraCineRegistryApi } from '../generated/api/cine-registry'
import { LibraConfigKeys, LibraConfigService } from './core/services/libra-config.service'

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'LibraView',
  },
  {
    path: 'login',
    component: LoginComponent,
    title: 'Login',
  },
  {
    path: 'settings',
    component: SettingsComponent,
    title: 'Settings',
    canActivate: [LibraAuthenticationGuard],
  },
  {
    path: 'cinematic',
    loadChildren: () =>
      import('./features/cinematic/cinematic.module').then((m) => m.CinematicModule),
    title: 'Cinematic',
    canActivate: [LibraAuthenticationGuard],
  },
  {
    path: '**',
    redirectTo: '',
    pathMatch: 'full',
  },
]

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideClientHydration(),
    provideAnimationsAsync(),

    provideHttpClient(withInterceptorsFromDi()),

    /*
      Interceptors
    */
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ResponseInterceptor,
      multi: true,
    },
    /*
      Services
    */
    provideAppInitializer(initApp()),
  ],
}

function initApp(): () => void {
  return () => {
    const tmdbApi = inject(TmdbApi)
    const libraMasterApi = inject(LibraMasterApi)
    const libraCineRegistryApi = inject(LibraCineRegistryApi)
    const libraConfig = inject(LibraConfigService)

    tmdbApi.configuration.credentials['sec0'] = () =>
      libraConfig.getResource<LibraConfigKeys.TMDB_TOKEN>(LibraConfigKeys.TMDB_TOKEN) ?? ''
    libraMasterApi.configuration.credentials['sec0'] = () =>
      libraConfig.getResource<LibraConfigKeys.LIBRA_TOKEN>(LibraConfigKeys.LIBRA_TOKEN) ?? ''
    libraCineRegistryApi.configuration.credentials['sec0'] = () =>
      libraConfig.getResource<LibraConfigKeys.LIBRA_TOKEN>(LibraConfigKeys.LIBRA_TOKEN) ?? ''
  }
}

const serverConfig: ApplicationConfig = {
  providers: [provideServerRendering()],
}

export const config = mergeApplicationConfig(appConfig, serverConfig)
