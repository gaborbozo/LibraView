import { TMDBImageDetailsDTO } from './tmdb-image-details.dto'

export interface TMDBDetailsDTO {
  images: TMDBImageDetailsDTO

  change_keys: Array<string>
}
