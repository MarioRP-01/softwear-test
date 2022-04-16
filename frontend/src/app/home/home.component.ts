import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from '../api/product.service';
import { ProductFilter } from '../model/data/product-filter';
import { PageableProduct } from '../model/pageable-product';
import { Product } from '../model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public products: Product[] = [];
  private totalPages: number = 0;
  private nextPage: number = 1

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.refresh();
  }

  /**
   * Call API REST to refresh website content.
   */
  refresh(): void {
    let filter = ProductFilter.OneByName;
    this.productService.getProductWithFilter(filter, this.nextPage).subscribe(
      page => this.updateContent(page)
    )
  }

  updateContent(page: PageableProduct): void {
    let length = this.products.length;

    this.products.splice(length, 0, ...page.products);
    this.totalPages = page.totalPages;
  }

  setNextPage(): void {
    if (this.totalPages > this.nextPage) {
      this.nextPage++;
    }   
  }

}
