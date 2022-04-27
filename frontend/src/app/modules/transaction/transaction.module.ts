import { NgModule } from '@angular/core';

import { TransactionRoutingModule } from './transaction-routing.module';
import { TransactionComponent } from './transaction.component';
import { CartComponent } from './pages/cart/cart.component';
import { ProductRowComponent } from './components/product-row/product-row.component';
import { WishlistCardComponent } from './components/wishlist-card/wishlist-card.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';
import { SharedModule } from '@app/shared/shared.module';


@NgModule({
  declarations: [
    TransactionComponent,
    CartComponent,  
    WishlistComponent,
    WishlistCardComponent,
    ProductRowComponent
  ],
  imports: [
    TransactionRoutingModule,
    SharedModule
  ]
})
export class TransactionModule { }
