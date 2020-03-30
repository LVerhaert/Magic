import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CardListComponent} from './card-list/card-list.component';
import {MagicCardsRoutingModule} from './magiccards-routing.module';
import {CardDetailsComponent} from './card-details/card-details.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    MagicCardsRoutingModule
  ],
  declarations: [
    CardListComponent,
    CardDetailsComponent
  ]
})
export class MagicCardsModule {
}
