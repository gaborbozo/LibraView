import { TMDBMovieDetailsDTO } from './tmdb-movie-details.dto'

export interface TMDBSearchMovieResponse {
  page: number

  results: Array<TMDBMovieDetailsDTO>

  total_pages: number

  total_results: number
}
