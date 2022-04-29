import { Component, Input, OnInit } from '@angular/core';
import { Product } from '@app/shared/model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-carousel',
  templateUrl: './product-carousel.component.html',
  styleUrls: ['./product-carousel.component.css']
})
export class ProductCarouselComponent implements OnInit {

  @Input()
  productSizes!: Product[];

  mainImage?: string;

  constructor() { }

  ngOnInit(): void {
    this.mainImage = this.productSizes[0].images?.[0]
  }

  setMainImage(image: string) {
    this.mainImage = image;
  }

  slideLeft() {
    document.getElementById('product-slide-wrapper')!.scrollLeft -= 180
  }

  slideRight() {
    document.getElementById('product-slide-wrapper')!.scrollLeft += 180
  }
  
}
