import { Component } from '@angular/core'
import { CinematicSearchMode } from '../search/search.component'
import { IFormBuilder, IFormGroup } from '@rxweb/types'
import {
  CinematicType,
  DefaultService as LibraCineRegistryApi,
  GeneralCinematicDTO,
} from '../../../../generated/api/cine-registry'
import { UntypedFormBuilder } from '@angular/forms'

interface SearchLibraryRequestForm {
  type: CinematicSearchMode
  title: string
}

@Component({
  selector: 'app-cinematic-library',
  templateUrl: './library.component.html',
  styleUrl: './library.component.scss',
  standalone: false,
})
export class CinematicLibraryComponent {
  form!: IFormGroup<SearchLibraryRequestForm>
  items?: GeneralCinematicDTO[]

  protected readonly CinematicType = CinematicType

  constructor(
    private libraCineRegistryApi: LibraCineRegistryApi,
    fb: UntypedFormBuilder,
  ) {
    this.form = (fb as IFormBuilder).group<SearchLibraryRequestForm>({
      type: ['MOVIE', []],
      title: ['', []],
    })
  }

  onSubmit() {
    if (!this.form.valid) return

    this.libraCineRegistryApi
      .getCinematicPage(
        this.form.controls.type.value!,
        {
          page: 0,
          size: 50,
          filters: [{ value: this.form.controls.title.value!, field: 'title' }],
        },
        false,
      )
      .subscribe((response) => (this.items = response))
  }
}
