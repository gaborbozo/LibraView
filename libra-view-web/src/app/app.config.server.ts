import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http'
import {
  APP_INITIALIZER,
  ApplicationConfig,
  mergeApplicationConfig,
  provideZoneChangeDetection,
} from '@angular/core'
import { provideClientHydration } from '@angular/platform-browser'
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async'
import { provideServerRendering } from '@angular/platform-server'
import { provideRouter, Routes } from '@angular/router'
import { ApiInterceptor } from './core/interceptors/api.interceptor'
import { AuthInterceptor } from './core/interceptors/auth.interceptor'
import { LibraAuthenticationGuard } from './core/services/libra-authentication-guard'
import { LibraInitializer } from './core/services/libra-initializer.service'
import { HomeComponent } from './features/home/home.component'
import { LoginComponent } from './features/login/login.component'

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
      useClass: ApiInterceptor,
      multi: true,
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    /*
      Services
    */
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [LibraInitializer],
      multi: true,
    },
  ],
}

export function initializeApp(libraInitializer: LibraInitializer) {
  return () => libraInitializer.getConfigurationDetails().subscribe()
}

const serverConfig: ApplicationConfig = {
  providers: [provideServerRendering()],
}

export const config = mergeApplicationConfig(appConfig, serverConfig)
