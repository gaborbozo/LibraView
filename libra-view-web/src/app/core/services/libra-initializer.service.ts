import { Injectable } from '@angular/core'
import { Observable, of, tap } from 'rxjs'
import { CinematicClientService } from '../../shared/client-service/cinematic-client.service'
import { TMDBGetDetailsResponse } from '../../shared/data-model/cinematic/integration/configuration/tmdb-get-details.response'

@Injectable({ providedIn: 'root' })
export class LibraInitializer {
  private configurationDetails?: TMDBGetDetailsResponse

  constructor(private cinematicClient: CinematicClientService) {}

  getConfigurationDetails(): Observable<TMDBGetDetailsResponse> {
    if (this.configurationDetails) {
      return of(this.configurationDetails)
    } else {
      return this.cinematicClient
        .configuration()
        .pipe(tap((cD) => (this.configurationDetails = cD)))
    }
  }
}
