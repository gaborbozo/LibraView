import { HttpClient, HttpParams } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'
import { convertToStringParams } from '../../core/helper-functions/http-client.helper'
import { TMDBDetailsDTO } from '../data-model/movie/integration/configuration/tmdb-details.dto'
import { TMDBSearchMovieRequest } from '../data-model/movie/integration/movies/tmdb-search-movie.request'
import { TMDBSearchMovieResponse } from '../data-model/movie/integration/movies/tmdb-search-movie.response'

@Injectable({
  providedIn: 'root',
})
export class MovieClientServiceService {
  private baseURL = '/movie_module'
  private movieURL = this.baseURL + '/movie'
  private tmdbURL = this.baseURL + '/tmdb'

  constructor(private http: HttpClient) {}

  /*
    Configuration
  */

  configuration(): Observable<TMDBDetailsDTO> {
    return this.http.get<TMDBDetailsDTO>(`${this.tmdbURL}/configuration`)
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
}
