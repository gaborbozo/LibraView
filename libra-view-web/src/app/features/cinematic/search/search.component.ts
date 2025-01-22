import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup } from '@angular/forms'
import { CinematicClientService } from '../../../shared/client-service/cinematic-client.service'
import { TMDBMovieDetailsDTO } from '../../../shared/data-model/cinematic/integration/movies/tmdb-movie-details.dto'
import { TMDBSearchMovieRequest } from '../../../shared/data-model/cinematic/integration/search/tmdb-search-movie.request'

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
})
export class SearchComponent implements OnInit {
  form!: FormGroup
  items: TMDBMovieDetailsDTO[] = []

  constructor(private cinematicClient: CinematicClientService) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', []),
    })
  }

  onSubmit() {
    if (this.form.valid) {
      this.cinematicClient
        .search({
          discriminator: 'MOVIE',
          query: this.form.controls['name'].value,
        } as TMDBSearchMovieRequest)
        .subscribe((data) => (this.items = data.results))
    }
  }
}
