import { Component, Input, OnInit } from '@angular/core'
import { LibraInitializer } from '../../../../core/services/libra-initializer.service'
import { CinematicClientService } from '../../../client-service/cinematic-client.service'
import { Cinematic } from '../../../data-model/cinematic/cinematic'
import { CinematicType } from '../../../data-model/cinematic/enums/cinematic-type'
import { TMDBMovieGeneral } from '../../../data-model/cinematic/integration/movies/tmdb-movie-general'

@Component({
  selector: 'app-cinematic-card',
  templateUrl: './cinematic-card.component.html',
  styleUrl: './cinematic-card.component.scss',
})
export class CinematicCardComponent implements OnInit {
  constructor(
    private libraInitializer: LibraInitializer,
    private cinematicClient: CinematicClientService,
  ) {}

  @Input() dataType!: 'TMDB_GENERAL' | 'CINEMATIC'
  @Input()
  item!: TMDBMovieGeneral | Cinematic

  imageBaseUrl?: string

  ngOnInit(): void {
    this.libraInitializer
      .getConfigurationDetails()
      .subscribe((data) => (this.imageBaseUrl = data.images.base_url))
  }

  addMovieItem(id: number) {
    this.cinematicClient.addCinematic({ cinematic: CinematicType.MOVIE, id: id }).subscribe()
  }

  cardDefinedWithTMDBGeneral(item: TMDBMovieGeneral | Cinematic): item is TMDBMovieGeneral {
    return this.dataType === 'TMDB_GENERAL'
  }

  cardDefinedWithCinematic(item: TMDBMovieGeneral | Cinematic): item is Cinematic {
    return this.dataType === 'CINEMATIC'
  }
}
