import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MagicCardsComponent} from '../magiccards/magiccards.component';
import {MagicCardDetailComponent} from '../magiccard-detail/magiccard-detail.component';

const routes: Routes = [
  {path: 'magiccards', component: MagicCardsComponent},
  {path: '', redirectTo: '/magiccards', pathMatch: 'full'},
  {path: 'magiccard/:id', component: MagicCardDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
