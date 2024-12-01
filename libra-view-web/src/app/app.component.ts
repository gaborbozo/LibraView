import { Component } from '@angular/core'
import { RouterModule, RouterOutlet } from '@angular/router'
import { LibraCommonModule } from './common/libra-common.module'
import { SharedModule } from './shared/shared.module'

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [SharedModule, LibraCommonModule, RouterOutlet, RouterModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {}
