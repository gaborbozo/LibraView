export interface Cinematic {
  discriminator: 'MOVIE' | 'SERIES'

  id: number

  tmdbId: number

  title: string

  releaseDate: Date

  overview: string

  posterPath: string

  backdropPath: string

  voteAverage: number

  voteCount: number

  popularity: number

  genreIds: Array<number>
}
