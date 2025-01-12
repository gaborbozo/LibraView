import { Component, OnInit } from '@angular/core'
import { FormControl, FormGroup } from '@angular/forms'
import { MovieClientServiceService } from '../../../shared/client-service/movie-client.service'

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
})
export class SearchComponent implements OnInit {
  form!: FormGroup

  constructor(private movieClientService: MovieClientServiceService) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      name: new FormControl('', []),
    })
  }

  onSubmit() {
    if (this.form.valid) {
      this.movieClientService
        .searchMovie({
          query: this.form.controls['name'].value,
        })
        .subscribe((data) => console.log(data))
    }
  }
}
