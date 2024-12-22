import { Directive, HostListener, Input } from '@angular/core'
import { MatDrawer } from '@angular/material/sidenav'

@Directive({
  selector: '[toggleDrawer]',
})
export class ToggleDrawerDirective {
  @Input('toggleDrawer') drawer!: MatDrawer

  @HostListener('click') onClick() {
    if (this.drawer) {
      this.drawer.toggle()
    } else {
      console.error('Drawer element not found')
    }
  }
}
