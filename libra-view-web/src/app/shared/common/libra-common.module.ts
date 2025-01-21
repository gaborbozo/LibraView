import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { MatButtonModule } from '@angular/material/button'
import { MatCardModule } from '@angular/material/card'
import { MatChipsModule } from '@angular/material/chips'
import { MatIconModule } from '@angular/material/icon'
import { CinematicCardComponent } from './components/cinematic-card/cinematic-card.component'
import { ToggleDrawerDirective } from './directives/toggle-drawer'

@NgModule({
  imports: [CommonModule, MatIconModule, MatButtonModule, MatCardModule, MatChipsModule],
  declarations: [
    /*
      Components
    */
    CinematicCardComponent,

    /*
      Directives
    */
    ToggleDrawerDirective,
  ],
  exports: [
    /*
      Components
    */
    CinematicCardComponent,

    /*
      Directives
    */
    ToggleDrawerDirective,
  ],
})
export class LibraCommonModule {}
