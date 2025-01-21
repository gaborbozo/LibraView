import { TMDBImageDetailsDTO } from './tmdb-image-details.dto'

export interface TMDBGetDetailsResponse {
  images: TMDBImageDetailsDTO

  change_keys: Array<string>
}
