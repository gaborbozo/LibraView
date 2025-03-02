import { IdRequest } from '../common/id.request'
import { CinematicType } from './enums/cinematic-type'

export interface CinematicRequest extends IdRequest {
  cinematic: CinematicType
}
