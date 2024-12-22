import { NgModule } from '@angular/core'
import { ToggleDrawerDirective } from './directives/toggle-drawer'

@NgModule({
  declarations: [ToggleDrawerDirective],
  exports: [ToggleDrawerDirective],
})
export class LibraCommonModule {}
