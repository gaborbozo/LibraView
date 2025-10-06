import { Component, Input, OnInit } from '@angular/core'
import { AliasCinematicSearchResponseItem } from '../../alias/cinematic.alias'
import {
  CinematicType,
  DefaultService as CineRegistryService,
} from '../../../../../generated/api/cine-registry'

@Component({
  selector: 'app-cinematic-card',
  templateUrl: './cinematic-card.component.html',
  styleUrl: './cinematic-card.component.scss',
  standalone: false,
})
export class CinematicCardComponent implements OnInit {
  @Input() dataType!: 'TMDB_GENERAL' | 'CINEMATIC'
  @Input() item!: AliasCinematicSearchResponseItem

  imageBaseUrl?: string

  constructor(private cineRegistryService: CineRegistryService) {}

  ngOnInit(): void {
    // this.libraInitializer
    //   .getConfigurationDetails()
    //   .subscribe((data) => (this.imageBaseUrl = data.images.base_url))
  }

  addMovieItem(id: number) {
    this.cineRegistryService.addCinematicToUserLibrary([id], CinematicType.Movie).subscribe()
  }

  // cardDefinedWithTMDBGeneral(item: TMDBMovieGeneral | Cinematic): item is TMDBMovieGeneral {
  //   return this.dataType === 'TMDB_GENERAL'
  // }
  //
  // cardDefinedWithCinematic(item: TMDBMovieGeneral | Cinematic): item is Cinematic {
  //   return this.dataType === 'CINEMATIC'
  // }
}
