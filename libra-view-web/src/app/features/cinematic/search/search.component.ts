import { Component } from '@angular/core'
import { UntypedFormBuilder } from '@angular/forms'
import { DefaultService as TmdbService } from '../../../../generated/api/tmdb'
import { AliasCinematicSearchResponse } from '../../../shared/common/alias/cinematic.alias'
import { PageEvent } from '@angular/material/paginator'
import { IFormBuilder, IFormGroup } from '@rxweb/types'

export type SearchCinemaType = 'MOVIE' | 'SERIES' | 'BOTH'

interface SearchRequestForm {
  type: SearchCinemaType
  title: string
}

@Component({
  selector: 'app-cinematic-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
  standalone: false,
})
export class CinematicSearchComponent {
  form!: IFormGroup<SearchRequestForm>
  searchResponse?: AliasCinematicSearchResponse

  constructor(
    private tmdbService: TmdbService,
    fb: UntypedFormBuilder,
  ) {
    this.form = (fb as IFormBuilder).group<SearchRequestForm>({
      type: ['MOVIE', []],
      title: ['', []],
    })
  }

  onSubmit(page?: PageEvent) {
    const pageNumber = page?.pageIndex ?? 0

    if (this.form.valid) {
      this.tmdbService
        .searchMulti(this.form.controls.title.value!, true, 'en', pageNumber + 1)
        .subscribe((response) => (this.searchResponse = response))
    }
  }
}
