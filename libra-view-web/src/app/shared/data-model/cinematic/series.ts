import { Cinematic } from './cinematic'

export interface Series extends Cinematic {
  discriminator: 'SERIES'
}
