import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TransactionRoutingModule } from './transaction-routing.module';
import { TransactionComponent } from './transaction.component';
import { CartComponent } from './pages/cart/cart.component';
import { ProductRowComponent } from './components/product-row/product-row.component';
import { WishlistCardComponent } from './components/wishlist-card/wishlist-card.component';
import { WishlistComponent } from './pages/wishlist/wishlist.component';


@NgModule({
  declarations: [
    TransactionComponent,
    CartComponent,  
    WishlistComponent,
    WishlistCardComponent,
    ProductRowComponent
  ],
  imports: [
    CommonModule,
    TransactionRoutingModule
  ]
})
export class TransactionModule { }
