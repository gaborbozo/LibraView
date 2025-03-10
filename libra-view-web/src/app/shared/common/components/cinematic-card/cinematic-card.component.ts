import { Component, Input, OnInit } from '@angular/core'
import { LibraInitializer } from '../../../../core/services/libra-initializer.service'
import { CinematicClientService } from '../../../client-service/cinematic-client.service'
import { TMDBMovieDetailsDTO } from '../../../data-model/cinematic/integration/movies/tmdb-movie-details.dto'

@Component({
  selector: 'app-cinematic-card',
  templateUrl: './cinematic-card.component.html',
  styleUrl: './cinematic-card.component.scss',
})
export class CinematicCardComponent implements OnInit {
  constructor(
    private libraInitializer: LibraInitializer,
    private movieClient: CinematicClientService,
  ) {}

  @Input()
  item!: TMDBMovieDetailsDTO

  imageBaseUrl?: string

  ngOnInit(): void {
    this.libraInitializer
      .getConfigurationDetails()
      .subscribe((data) => (this.imageBaseUrl = data.images.base_url))
  }

  addMovieItem(id: number) {
    this.movieClient.addCinematic({ id: id }).subscribe()
  }
}
