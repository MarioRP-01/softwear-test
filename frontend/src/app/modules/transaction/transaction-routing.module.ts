import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WishlistCardComponent } from './components/wishlist-card/wishlist-card.component';
import { CartComponent } from './pages/cart/cart.component';

const routes: Routes = [
  { path: '', component: CartComponent },
  { path: 'wishlist', component: WishlistCardComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TransactionRoutingModule { }
