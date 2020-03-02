import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {MagicCardListComponent} from './magiccard-list/magiccard-list.component';
import {MagicCardsRoutingModule} from './magiccards-routing.module';
import {MagicCardDetailsComponent} from './card-details/card-details.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MagicCardsRoutingModule
  ],
  declarations: [
    MagicCardListComponent,
    MagicCardDetailsComponent
  ]
})
export class MagicCardsModule {
}
