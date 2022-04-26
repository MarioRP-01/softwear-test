import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProfileComponent } from './pages/profile/profile.component';
import { PurchaseHistoryComponent } from './pages/purchase-history/purchase-history.component';

const routes: Routes = [
  { path: '', component: ProfileComponent },
  { path: 'purchase-history', component: PurchaseHistoryComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
