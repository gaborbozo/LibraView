import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup } from '@angular/forms'
import { CinematicClientService } from '../../../shared/client-service/cinematic-client.service'
import { TMDBMovieGeneral } from '../../../shared/data-model/cinematic/integration/movies/tmdb-movie-general'
import { TMDBSearchMovieRequest } from '../../../shared/data-model/cinematic/integration/search/tmdb-search-movie.request'

@Component({
  selector: 'app-cinematic-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
})
export class CinematicSearchComponent implements OnInit {
  form!: FormGroup
  items: TMDBMovieGeneral[] = []

  constructor(private cinematicClient: CinematicClientService) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', []),
    })
  }

  onSubmit() {
    if (this.form.valid) {
      this.cinematicClient
        .searchCinematics({
          discriminator: 'MOVIE',
          query: this.form.controls['name'].value,
        } as TMDBSearchMovieRequest)
        .subscribe((response) => (this.items = response.results))
    }
  }
}
