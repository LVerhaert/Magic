import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CardListComponent} from '../components/card-list/card-list.component';
import {CardRelatedComponent} from "../components/card-related/card-related.component";
import {CardDetailsComponent} from "../components/card-details/card-details.component";
import {UserFormComponent} from '../components/user-form/user-form.component';

const routes: Routes = [
  {path: 'magiccards', component: CardListComponent},
  {path: 'magiccard/{id}', component: CardDetailsComponent},
  {path: 'magiccard/{id}/related', component: CardRelatedComponent},
  {path: 'adduser', component: UserFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
