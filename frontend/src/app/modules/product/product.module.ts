import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductViewComponent } from './pages/product-view/product-view.component';
import { ProductCarouselComponent } from './components/product-carousel/product-carousel/product-carousel.component';
import { ProductSizeComponent } from './components/product-size/product-size/product-size.component';


@NgModule({
  declarations: [
    ProductViewComponent,
    ProductCarouselComponent,
    ProductSizeComponent
  ],
  imports: [
    CommonModule,
    ProductRoutingModule
  ]
})
export class ProductModule { }
