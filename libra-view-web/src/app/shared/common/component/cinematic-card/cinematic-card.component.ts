import { Component, Input, OnInit } from '@angular/core'
import {
  AliasMovieSearchResponseItem,
  AliasSeriesSearchResponseItem,
} from '../../../../constants/cinematic.alias'
import {
  CinematicType,
  DefaultService as LibraCineRegistryApi,
} from '../../../../../generated/api/cine-registry'
import { CinematicMetaService } from '../../../../core/services/cinematic-meta.service'

@Component({
  selector: 'app-cinematic-card',
  templateUrl: './cinematic-card.component.html',
  styleUrl: './cinematic-card.component.scss',
  standalone: false,
})
export class CinematicCardComponent implements OnInit {
  @Input() searchItem?:
    | { type: 'MOVIE'; item: AliasMovieSearchResponseItem }
    | { type: 'SERIES'; item: AliasSeriesSearchResponseItem }

  imageBaseUrl?: string

  constructor(
    private libraCineRegistryApi: LibraCineRegistryApi,
    private cinematicMeta: CinematicMetaService,
  ) {
    this.imageBaseUrl = cinematicMeta.getBaseUrl()
  }

  ngOnInit(): void {
    // this.libraInitializer
    //   .getConfigurationDetails()
    //   .subscribe((data) => (this.imageBaseUrl = data.images.base_url))
  }

  addMovieItem(id: number) {
    this.libraCineRegistryApi.addCinematicToUserLibrary([id], CinematicType.Movie).subscribe()
  }
}
