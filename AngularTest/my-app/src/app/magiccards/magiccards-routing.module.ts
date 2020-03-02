import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MagicCardListComponent} from './magiccard-list/magiccard-list.component';
import {MagicCardDetailsComponent} from './card-details/card-details.component';

const magicCardsRoutes: Routes = [
  {path: 'magiccards', component: MagicCardListComponent},
  {path: 'magiccard/{idString}', component: MagicCardDetailsComponent}
];

@NgModule({
  imports: [
    RouterModule.forChild(magicCardsRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class MagicCardsRoutingModule {
}
