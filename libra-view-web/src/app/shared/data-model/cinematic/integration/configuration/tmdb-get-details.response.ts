import { TMDBImageDetails } from './tmdb-image-details'

export interface TMDBGetDetailsResponse {
  images: TMDBImageDetails

  change_keys: Array<string>
}
