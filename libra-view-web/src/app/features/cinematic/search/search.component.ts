import { Component } from '@angular/core'
import { UntypedFormBuilder } from '@angular/forms'
import {
  AliasMovieSearchResponse,
  AliasSeriesSearchResponse,
} from '../../../constants/cinematic.alias'
import { PageEvent } from '@angular/material/paginator'
import { IFormBuilder, IFormGroup } from '@rxweb/types'
import { DefaultService as TmdbApi } from '../../../../generated/api/tmdb'
import { CinematicType } from '../../../../generated/api/cine-registry'

export type CinematicSearchMode = CinematicType

interface SearchRequestForm {
  type: CinematicSearchMode
  title: string
}

export type CinematicSearchResult =
  | { mode: 'MOVIE'; response: AliasMovieSearchResponse }
  | { mode: 'SERIES'; response: AliasSeriesSearchResponse }

@Component({
  selector: 'app-cinematic-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
  standalone: false,
})
export class CinematicSearchComponent {
  form!: IFormGroup<SearchRequestForm>
  searchResponse?: CinematicSearchResult

  protected readonly CinematicType = CinematicType

  constructor(
    private tmdbService: TmdbApi,
    fb: UntypedFormBuilder,
  ) {
    this.form = (fb as IFormBuilder).group<SearchRequestForm>({
      type: ['MOVIE', []],
      title: ['', []],
    })
  }

  onSubmit(page?: PageEvent) {
    if (!this.form.valid) return

    const pageNumber = page?.pageIndex ?? 0
    const mode = this.form.controls.type.value!
    const title = this.form.controls.title.value!

    switch (mode) {
      case 'MOVIE': {
        this.tmdbService
          .searchMovie(title, true, 'en', undefined, pageNumber + 1)
          .subscribe((response) => (this.searchResponse = { mode: mode, response: response }))
        break
      }
      case 'SERIES': {
        this.tmdbService
          .searchTv(title, undefined, true, 'en', pageNumber + 1)
          .subscribe((response) => (this.searchResponse = { mode: mode, response: response }))
        break
      }
    }
  }
}
