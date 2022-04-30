import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductViewComponent } from './pages/product-view/product-view.component';
import { ProductResolver, ProductSizesResolver, UserLoggedResolver, WishlistResolver } from '@app/core/resolver';
import { AvailableSizesResolver } from './resolver';
import { ProductGuard } from '@app/core/authentication';

const routes: Routes = [
  {
    path: ':productId',
    canActivate: [ProductGuard],
    component: ProductViewComponent,
    resolve: {
      productSizes: ProductSizesResolver,
      user: UserLoggedResolver,
      availablesSizes: AvailableSizesResolver,
      wishlist: WishlistResolver
    }
  },
  
  { path: '', redirectTo: '/home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
