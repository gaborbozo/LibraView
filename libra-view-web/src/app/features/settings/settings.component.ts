import { Component } from '@angular/core'
import { IFormBuilder, IFormGroup } from '@rxweb/types'
import { LibraCommonModule } from '../../shared/common/libra-common.module'
import { SharedModule } from '../../shared/shared.module'
import { LibraConfigKeys, LibraConfigService } from '../../core/services/libra-config.service'
import { UntypedFormBuilder } from '@angular/forms'

interface SettingsForm {
  tmdbToken: string
}

@Component({
  selector: 'app-login',
  imports: [SharedModule, LibraCommonModule],
  templateUrl: './settings.component.html',
  styleUrl: './settings.component.scss',
  standalone: true,
})
export class SettingsComponent {
  form!: IFormGroup<SettingsForm>

  constructor(
    private config: LibraConfigService,
    fb: UntypedFormBuilder,
  ) {
    this.form = (fb as IFormBuilder).group<SettingsForm>({
      tmdbToken: [config.getResource(LibraConfigKeys.TMDB_TOKEN) ?? '', []],
    })
  }

  onSubmit() {
    this.config.setResource(LibraConfigKeys.TMDB_TOKEN, this.form.controls.tmdbToken.value!)
  }
}
