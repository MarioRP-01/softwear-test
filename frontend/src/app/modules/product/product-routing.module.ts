import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductViewComponent } from './pages/product-view/product-view.component';
import { ProductResolver, ProductSizesResolver, UserLoggedResolver, WishlistResolver } from '@app/core/resolver';
import { AvailableSizesResolver } from './resolver';

const routes: Routes = [{
    path: '',
    component: ProductViewComponent,
    resolve: {
      productSizes: ProductSizesResolver,
      user: UserLoggedResolver,
      availablesSizes: AvailableSizesResolver,
      wishlist: WishlistResolver
    }
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProductRoutingModule { }
