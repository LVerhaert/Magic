import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms'; // <- NgModel
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {MagicCardsComponent} from './magiccards/magiccards.component';
import {MagicCardDetailComponent} from './magiccard-detail/magiccard-detail.component';
import {MessagesComponent} from './messages/messages.component';
import {AppRoutingModule} from './app-routing/app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {MagicCardSearchComponent} from './magiccard-search/magiccard-search.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

@NgModule({
  declarations: [
    AppComponent,
    MagicCardsComponent,
    MagicCardDetailComponent,
    MessagesComponent,
    DashboardComponent,
    MagicCardSearchComponent
  ],
  imports: [
    MatPaginatorModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
