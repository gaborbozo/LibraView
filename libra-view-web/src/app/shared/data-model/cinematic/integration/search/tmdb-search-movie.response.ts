import { TMDBMovieDetailsDTO } from '../movies/tmdb-movie-details.dto'
import { TMDBSearchResponse } from './tmdb-search.response'

export interface TMDBSearchMovieResponse extends TMDBSearchResponse {
  discriminator: 'MOVIE'

  page: number

  results: Array<TMDBMovieDetailsDTO>

  total_pages: number

  total_results: number
}
