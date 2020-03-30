import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CardListComponent} from './card-list/card-list.component';
import {CardDetailsComponent} from './card-details/card-details.component';
import {CardRelatedComponent} from './card-related/card-related.component';

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
