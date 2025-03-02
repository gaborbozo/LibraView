import { HttpClient, HttpParams } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable, of } from 'rxjs'
import { convertToStringParams } from '../../core/helper-functions/http-client.helper'
import { CinematicRequest } from '../data-model/cinematic/cinematic.request'
import { TMDBGetDetailsResponse } from '../data-model/cinematic/integration/configuration/tmdb-get-details.response'
import { TMDBSearchMovieRequest } from '../data-model/cinematic/integration/search/tmdb-search-movie.request'
import { TMDBSearchMovieResponse } from '../data-model/cinematic/integration/search/tmdb-search-movie.response'
import { TMDBSearchRequest } from '../data-model/cinematic/integration/search/tmdb-search.request'
import { TMDBSearchResponse } from '../data-model/cinematic/integration/search/tmdb-search.response'
import { SimpleResponse } from '../data-model/common/simple.response'

export type SearchResponseMapper<T extends TMDBSearchRequest> = T extends TMDBSearchMovieRequest
  ? TMDBSearchMovieResponse
  : TMDBSearchResponse

@Injectable({
  providedIn: 'root',
})
export class CinematicClientService {
  private baseURL = '/cinematic_module'
  private cinematicURL = this.baseURL + '/cinematic'
  private tmdbURL = this.baseURL + '/tmdb'

  constructor(private http: HttpClient) {}

  /*
    Configuration
  */

  configuration(): Observable<TMDBGetDetailsResponse> {
    return this.http.get<TMDBGetDetailsResponse>(`${this.tmdbURL}/configuration`)
  }

  /*
    Movie
  */

  search<T extends TMDBSearchRequest>(request: T): Observable<SearchResponseMapper<T>> {
    const params = new HttpParams({
      fromObject: convertToStringParams(request),
    })

    if (request.discriminator === 'MOVIE') {
      return this.http.get<SearchResponseMapper<T>>(`${this.tmdbURL}/search`, { params })
    }

    return of({} as SearchResponseMapper<T>)
  }

  addCinematic(request: CinematicRequest): Observable<SimpleResponse> {
    return this.http.post<SimpleResponse>(`${this.cinematicURL}/addCinematic`, request)
  }
}
