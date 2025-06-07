import { Cinematic } from './cinematic'

export interface Movie extends Cinematic {
  discriminator: 'MOVIE'
}
