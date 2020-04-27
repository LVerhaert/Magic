import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MagicCardsComponent} from '../magiccards/magiccards.component';
import {MagicCardDetailComponent} from '../magiccard-detail/magiccard-detail.component';
import {PlayersComponent} from '../players/players.component';
import {PlayerDetailComponent} from '../player-detail/player-detail.component';
import {DecksComponent} from '../decks/decks.component';

const routes: Routes = [
  {path: 'magiccards', component: MagicCardsComponent}, // http://localhost:4200/magiccards
  {path: '', redirectTo: '/magiccards', pathMatch: 'full'}, // http://localhost:4200
  {path: 'magiccard/:id', component: MagicCardDetailComponent}, // http://localhost:4200/magiccard/CARD_ID
  {path: 'players', component: PlayersComponent}, // http://localhost:4200/players
  {path: 'player/:playerid/decks', component: DecksComponent}, // http://localhost:4200/player/PLAYER_ID/decks
  {path: 'player/:playerid/deck/:deckid', component: MagicCardsComponent}, // http://localhost:4200/player/PLAYER_ID/deck/DECK_ID
  {path: 'player/:playerid', component: PlayerDetailComponent}, // http://localhost:4200/player/PLAYER_ID
  {path: 'player/:playerid/maincollection', component: MagicCardsComponent} // http://localhost:4200/player/PLAYERID/maincollection
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
