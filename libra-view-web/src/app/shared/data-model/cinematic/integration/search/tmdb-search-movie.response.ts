import { TMDBMovieGeneral } from '../movies/tmdb-movie-general'
import { TMDBSearchResponse } from './tmdb-search.response'

export interface TMDBSearchMovieResponse extends TMDBSearchResponse {
  discriminator: 'MOVIE'

  page: number

  results: Array<TMDBMovieGeneral>

  total_pages: number

  total_results: number
}
