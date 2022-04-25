import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProductRoutingModule } from './product-routing.module';
import { ProductComponent } from './product.component';
import { ProductSizeComponent } from './components/product-size/product-size/product-size.component';
import { ProductCarouselComponent } from './components/product-carousel/product-carousel/product-carousel.component';


@NgModule({
  declarations: [
    ProductComponent,
    ProductCarouselComponent,
    ProductSizeComponent
  ],
  imports: [
    CommonModule,
    ProductRoutingModule
  ]
})
export class ProductModule { }
