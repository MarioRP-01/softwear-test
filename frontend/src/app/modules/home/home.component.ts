import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ProductService } from '@app/core/api/product.service';
import { TransactionService } from '@app/core/api/transaction.service';
import { ProductFilter } from '@app/shared/model/data/product-filter';
import { TransactionType } from '@app/shared/model/data/transaction-type';
import { PageableProduct } from '@app/shared/model/pageable-product';
import { Product } from '@app/shared/model/product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public products: Product[] = [];
  public wishlistId: number = -1;

  public totalPages: number = 0;
  public nextPage: number = 1

  constructor(private productService: ProductService, private transactionService: TransactionService) { }

  ngOnInit(): void {
    this.refresh();
  }

  /**
   * Call API REST to refresh website content.
   */
  refresh(): void {

    this.setProducts()
    this.setWishlist()
  }

  updateContent(page: PageableProduct): void {

    let length = this.products.length;

    this.products.splice(length, 0, ...page.products);
    this.totalPages = page.totalPages;
  }

  setProducts(): void {
    let filter = ProductFilter.OneByName;
    this.productService.getProductWithFilter(filter, this.nextPage).subscribe(
      page => this.updateContent(page)
    )
  }

  setWishlist(): void {

    this.transactionService.getSpecialTransactionId(TransactionType.WISHLIST).subscribe(
      wishlistId => this.wishlistId = wishlistId[0]
    );
  }

  loadNextPage(): void {
    
    if (this.totalPages > this.nextPage) {
      this.nextPage++;   
      this.refresh();
    }
  }

}
