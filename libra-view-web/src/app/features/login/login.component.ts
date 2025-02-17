import { Component } from '@angular/core'
import { FormBuilder, FormGroup, Validators } from '@angular/forms'
import { LibraAuthenticationService } from '../../core/services/libra-authentication-service'
import { LibraCommonModule } from '../../shared/common/libra-common.module'
import { SharedModule } from '../../shared/shared.module'

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [SharedModule, LibraCommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  form: FormGroup

  constructor(
    private authenticationService: LibraAuthenticationService,
    private fb: FormBuilder,
  ) {
    this.form = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    })
  }

  onSubmit() {
    this.authenticationService.login({
      username: this.form.controls['username'].value,
      password: this.form.controls['password'].value,
    })
  }
}
