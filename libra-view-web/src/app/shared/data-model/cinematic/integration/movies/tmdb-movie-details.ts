import { TMDBProductionCompany } from '../general/tmdb-production-company'
import { TMDBGenre } from '../genres/tmdb-genre'

export interface TMDBMovieDetails {
  backdrop_path: string

  budget: number

  genres: Array<TMDBGenre>

  homepage: string

  id: string

  imdb_id: string

  overview: string

  popularity: number

  poster_path: string

  production_companies: Array<TMDBProductionCompany>

  release_date: string

  revenue: number

  runtime: number

  status: string

  title: string

  vote_average: number

  vote_count: number
}
