import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserInfoComponent } from './pages/user-info/user-info.component';
import { PurchaseHistoryComponent } from './pages/purchase-history/purchase-history.component';
import { UserComponent } from './user.component';

const routes: Routes = [
  { path: '', 
    component: UserComponent,
    children:[
      { path: '', redirectTo: 'user-info' },
      { path: 'user-info', component: UserInfoComponent },
      { path: 'purchase-history', component: PurchaseHistoryComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
