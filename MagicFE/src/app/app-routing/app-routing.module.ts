import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {MagicCardsComponent} from "../magiccards/magiccards.component";
import {MagicCardDetailComponent} from "../magiccard-detail/magiccard-detail.component";
import {PlayersComponent} from "../players/players.component";
import {PlayerDetailComponent} from "../player-detail/player-detail.component";

const routes: Routes = [
  {path: "magiccards", component: MagicCardsComponent},
  {path: "", redirectTo: "/magiccards", pathMatch: "full"},
  {path: "magiccard/:id", component: MagicCardDetailComponent},
  {path: "players", component: PlayersComponent},
  {path: "player/:playerid/deck/:deckid", component: MagicCardsComponent},
  {path: "player/:playerid", component: PlayerDetailComponent},
  {path: "player/:playerid/maincollection", component: MagicCardsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
