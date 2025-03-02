import { Component } from '@angular/core'
import { RouterModule, RouterOutlet } from '@angular/router'
import { LibraAuthenticationService } from './core/services/libra-authentication-service'
import { LibraCommonModule } from './shared/common/libra-common.module'
import { SharedModule } from './shared/shared.module'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SharedModule, LibraCommonModule, RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  constructor(public authenticationService: LibraAuthenticationService) {}
}
