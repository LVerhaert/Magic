import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CardListComponent} from '../components/card-list/card-list.component';
import {CardDetailsComponent} from '../components/card-details/card-details.component';
import {CardRelatedComponent} from '../components/card-related/card-related.component';

const magicCardsRoutes: Routes = [
  {path: 'magiccards', component: CardListComponent},
  {path: 'magiccard/{id}', component: CardDetailsComponent},
  {path: 'magiccard/{id}/related', component: CardRelatedComponent},
];

@NgModule({
  imports: [RouterModule.forChild(magicCardsRoutes)],
  exports: [RouterModule]
})
export class MagicCardsRoutingModule {
}
