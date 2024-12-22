import { CommonModule } from '@angular/common'
import { NgModule } from '@angular/core'
import { AngularMaterialModule } from './angular-material/angular-material.module'

@NgModule({
  exports: [CommonModule, AngularMaterialModule],
})
export class SharedModule {}
