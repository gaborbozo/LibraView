import { NgModule } from '@angular/core'
import { RouterModule, Routes } from '@angular/router'
import { SharedModule } from '../../shared/shared.module'
import { OverviewComponent } from './overview/overview.component'
import { SearchComponent } from './search/search.component'

const routes: Routes = [
  { path: '', component: OverviewComponent, title: 'Movie' },
  { path: 'search', component: SearchComponent, title: 'Movie search' },
]

@NgModule({
  declarations: [OverviewComponent, SearchComponent],
  imports: [SharedModule, RouterModule.forChild(routes)],
})
export class MovieModule {}
