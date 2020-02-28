import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MagicCardListComponent} from './magiccard-list/magiccard-list.component';
import {UserFormComponent} from './user-form/user-form.component';


const routes: Routes = [
  {path: 'magiccards', component: MagicCardListComponent},
  {path: 'adduser', component: UserFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
