import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms'; // <- NgModel
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {MagicCardsComponent} from './magiccards/magiccards.component';
import {MagicCardDetailComponent} from './magiccard-detail/magiccard-detail.component';
import {MessagesComponent} from './messages/messages.component';

import {AppRoutingModule} from './app-routing/app-routing.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSliderModule} from '@angular/material/slider';
import {MatSidenavModule} from '@angular/material/sidenav';
import {PlayersComponent} from './players/players.component';
import {PlayerDetailComponent} from './player-detail/player-detail.component';
import {DecksComponent} from './decks/decks.component';
import {MagiccardsSearchComponent} from './magiccards-search/magiccards-search.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
// import {PlayersSearchComponent} from './players-search/players-search.component';
// import {DecksSearchComponent} from './decks-search/decks-search.component';

@NgModule({
  declarations: [
    AppComponent,
    MagicCardsComponent,
    MagicCardDetailComponent,
    MessagesComponent,
    PlayersComponent,
    PlayerDetailComponent,
    DecksComponent,
    MagiccardsSearchComponent,
    // PlayersSearchComponent,
    // DecksSearchComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatListModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatPaginatorModule,
    MatSliderModule,
    MatSidenavModule,
    MatTableModule,
    MatTableModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
