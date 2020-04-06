import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing/app-routing.module';
import {AppComponent} from './app.component';
import {UserFormComponent} from './components/user-form/user-form.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {MagicCardService} from './services/magiccard.service';
import {MagicCardsModule} from './magiccard.module';
import {Router} from '@angular/router';
import { CardRelatedComponent } from './components/card-related/card-related.component';

@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    CardRelatedComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MagicCardsModule
  ],
  providers: [MagicCardService],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(router: Router) {
  }
}
