import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { PurchaseHistoryComponent } from './pages/purchase-history/purchase-history.component';
import { ProfilePictureComponent } from './components/profile-picture/profile-picture.component';
import { AccountDetailsComponent } from './components/account-details/account-details.component';
import { TransactionCardComponent } from './components/transaction-card/transaction-card.component';


@NgModule({
  declarations: [
    UserComponent,
    ProfileComponent,
    PurchaseHistoryComponent,
    ProfilePictureComponent,
    AccountDetailsComponent,
    TransactionCardComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule
  ]
})
export class UserModule { }
