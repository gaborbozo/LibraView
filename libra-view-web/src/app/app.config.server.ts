import {
  ApplicationConfig,
  mergeApplicationConfig,
  provideZoneChangeDetection,
} from '@angular/core'
import { provideClientHydration } from '@angular/platform-browser'
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async'
import { provideServerRendering } from '@angular/platform-server'
import { provideRouter, Routes } from '@angular/router'
import { HomeComponent } from './features/home/home.component'

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'LibraView',
  },
  {
    path: 'movie',
    loadChildren: () => import('./features/movie/movie.module').then((m) => m.MovieModule),
    title: 'Movie',
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
  ],
}

const serverConfig: ApplicationConfig = {
  providers: [provideServerRendering()],
}

export const config = mergeApplicationConfig(appConfig, serverConfig)
