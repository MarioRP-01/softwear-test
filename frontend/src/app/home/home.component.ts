import { Component, OnInit } from '@angular/core';
import { ProductService } from '../api/product.service';
import { Product } from '../model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private productService: ProductService) { }

  product : Product[] = [];

  ngOnInit(): void {
    refresh();
  }

}

/**
   * Call API REST to refresh website content.
   */
function refresh() {
  throw new Error('Function not implemented.');
}

