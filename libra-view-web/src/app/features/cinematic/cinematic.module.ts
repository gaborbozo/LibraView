import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { LibraCommonModule } from '../../shared/common/libra-common.module'
import { SharedModule } from '../../shared/shared.module'
import { OverviewComponent } from './overview/overview.component'
import { SearchComponent } from './search/search.component'

const routes: Routes = [
  { path: '', component: OverviewComponent, title: 'Cinematic' },
  { path: 'search', component: SearchComponent, title: 'Cinematic search' },
]

@NgModule({
  declarations: [OverviewComponent, SearchComponent],
  imports: [SharedModule, RouterModule.forChild(routes), LibraCommonModule],
})
export class CinematicModule {}
