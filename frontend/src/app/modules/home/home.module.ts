import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { ProductCardComponent } from './components/product-card/product-card.component';
import { HomeUsComponent } from './pages/home-us/home-us.component';
import { HomeComponent } from './home.component';
import { AboutComponent } from './pages/about/about.component';


@NgModule({
  declarations: [
    ProductCardComponent,
    HomeComponent,
    HomeUsComponent,
    AboutComponent
  ],
  imports: [
    CommonModule,
    HomeRoutingModule
  ]
})
export class HomeModule { }
