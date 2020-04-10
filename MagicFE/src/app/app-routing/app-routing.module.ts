import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MagicCardsComponent} from '../magiccards/magiccards.component';
import {MagicCardDetailComponent} from '../magiccard-detail/magiccard-detail.component';
// import {MagicCardSearchComponent} from '../magiccard-search/magiccard-search.component';

const routes: Routes = [
  {path: 'magiccards', component: MagicCardsComponent},
  // {path: 'magiccards/search', component: MagicCardSearchComponent},
  {path: '', redirectTo: '/magiccards', pathMatch: 'full'},
  {path: 'magiccard/:id', component: MagicCardDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
