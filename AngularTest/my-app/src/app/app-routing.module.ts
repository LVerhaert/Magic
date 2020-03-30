import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CardListComponent} from './magiccards/card-list/card-list.component';
import {CardRelatedComponent} from "./magiccards/card-related/card-related.component";
import {CardDetailsComponent} from "./magiccards/card-details/card-details.component";
import {UserFormComponent} from './user-form/user-form.component';

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
