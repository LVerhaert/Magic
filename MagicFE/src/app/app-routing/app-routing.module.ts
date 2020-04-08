import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MagicCardsComponent} from '../magiccards/magiccards.component';
import {DashboardComponent} from '../dashboard/dashboard.component';
import {MagicCardDetailComponent} from '../magiccard-detail/magiccard-detail.component';

const routes: Routes = [
  {path: 'magiccards', component: MagicCardsComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'detail/:id', component: MagicCardDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
