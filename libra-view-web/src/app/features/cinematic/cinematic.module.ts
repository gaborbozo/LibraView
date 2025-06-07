import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { LibraCommonModule } from '../../shared/common/libra-common.module'
import { SharedModule } from '../../shared/shared.module'
import { CinematicLibraryComponent } from './library/library.component'
import { CinematicOverviewComponent } from './overview/overview.component'
import { CinematicSearchComponent } from './search/search.component'

const routes: Routes = [
  { path: '', component: CinematicOverviewComponent, title: 'Cinematic' },
  { path: 'search', component: CinematicSearchComponent, title: 'Search' },
  { path: 'library', component: CinematicLibraryComponent, title: 'Library' },
]

@NgModule({
  declarations: [CinematicOverviewComponent, CinematicSearchComponent, CinematicLibraryComponent],
  imports: [SharedModule, RouterModule.forChild(routes), LibraCommonModule],
})
export class CinematicModule {}
