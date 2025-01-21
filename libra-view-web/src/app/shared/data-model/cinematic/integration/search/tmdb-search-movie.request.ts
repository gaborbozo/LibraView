export interface TMDBSearchMovieRequest {
  query: string

  includeAdult?: boolean

  language?: string

  page?: number
}
