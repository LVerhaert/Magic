import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MagicCardListComponent} from './magiccard-list/magiccard-list.component';
import {UserFormComponent} from './user-form/user-form.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {MagicCardService} from './magiccard.service';
import {CardDetailsComponent} from './card-details/card-details.component';

@NgModule({
  declarations: [
    AppComponent,
    MagicCardListComponent,
    UserFormComponent,
    CardDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [MagicCardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
