import { TMDBSearchRequest } from './tmdb-search.request'

export interface TMDBSearchMovieRequest extends TMDBSearchRequest {
  discriminator: 'MOVIE'

  query: string

  includeAdult?: boolean

  language?: string

  page?: number
}
