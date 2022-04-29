import { NgModule } from '@angular/core';

import { ProductRoutingModule } from './product-routing.module';
import { ProductComponent } from './product.component';
import { ProductSizeComponent } from './components/product-size/product-size.component';
import { ProductCarouselComponent } from './components/product-carousel/product-carousel.component';
import { SharedModule } from '@app/shared/shared.module';
import { ProductViewComponent } from './pages/product-view/product-view.component';
import { TransactionsInteractionComponent } from './components/transactions-interaction/transactions-interaction.component';


@NgModule({
  declarations: [
    ProductComponent,
    ProductViewComponent,
    ProductCarouselComponent,
    ProductSizeComponent,
    TransactionsInteractionComponent
  ],
  imports: [
    SharedModule,
    ProductRoutingModule
  ]
})
export class ProductModule { }
