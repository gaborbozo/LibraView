import { Injectable } from '@angular/core'
import { Observable, of, tap } from 'rxjs'
import { MovieClientServiceService } from '../../shared/client-service/movie-client.service'
import { TMDBDetailsDTO } from '../../shared/data-model/movie/integration/configuration/tmdb-details.dto'

@Injectable({ providedIn: 'root' })
export class LibraInitializer {
  private configurationDetails?: TMDBDetailsDTO

  constructor(private movieClientServiceService: MovieClientServiceService) {}

  getConfigurationDetails(): Observable<TMDBDetailsDTO> {
    if (this.configurationDetails) {
      return of(this.configurationDetails)
    } else {
      return this.movieClientServiceService
        .configuration()
        .pipe(tap((cD) => (this.configurationDetails = cD)))
    }
  }
}
