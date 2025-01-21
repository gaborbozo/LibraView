import { HttpClient, HttpParams } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { convertToStringParams } from '../../core/helper-functions/http-client.helper'
import { SimpleResponse } from '../data-model/cinematic/empty.response'
import { IdRequest } from '../data-model/cinematic/id.request'
import { TMDBGetDetailsResponse } from '../data-model/cinematic/integration/configuration/tmdb-get-details.response'
import { TMDBSearchMovieRequest } from '../data-model/cinematic/integration/search/tmdb-search-movie.request'
import { TMDBSearchMovieResponse } from '../data-model/cinematic/integration/search/tmdb-search-movie.response'

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

  searchMovie(request: TMDBSearchMovieRequest): Observable<TMDBSearchMovieResponse> {
    const params = new HttpParams({
      fromObject: convertToStringParams(request),
    })
    return this.http.get<TMDBSearchMovieResponse>(`${this.tmdbURL}/search`, { params })
  }

  addCinematic(request: IdRequest): Observable<SimpleResponse> {
    return this.http.post<SimpleResponse>(`${this.cinematicURL}/addCinematic`, request)
  }
}
