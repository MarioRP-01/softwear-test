import { NgModule } from '@angular/core';

import { UserRoutingModule } from './user-routing.module';
import { UserComponent } from './user.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { PurchaseHistoryComponent } from './pages/purchase-history/purchase-history.component';
import { ProfilePictureComponent } from './components/profile-picture/profile-picture.component';
import { AccountDetailsComponent } from './components/account-details/account-details.component';
import { TransactionCardComponent } from './components/transaction-card/transaction-card.component';
import { SharedModule } from '@app/shared/shared.module';


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
    UserRoutingModule,
    SharedModule
  ]
})
export class UserModule { }
