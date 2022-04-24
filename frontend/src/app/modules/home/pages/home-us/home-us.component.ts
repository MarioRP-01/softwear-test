import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { ProductService, TransactionService } from '@app/core/api'

import { Transaction, PageableProduct, Product } from '@app/shared/model';
import { ProductFilter, TransactionType } from '@app/shared/data-type';
import { AuthService } from '@app/core/authentication';

@Component({
  selector: 'app-home-us',
  templateUrl: './home-us.component.html',
  styleUrls: ['./home-us.component.css']
})
export class HomeUsComponent implements OnInit {

  public products: Product[] = [];
  
  public totalPages: number = 0;
  public nextPage: number = 1;
  public $wishlist!: Observable<Transaction>

  public activeSesion!: boolean;

  @Input()
  type:string = '';


  

  constructor(
    private productService: ProductService,
    private transactionService: TransactionService,
    private authService: AuthService

    ) { }

  ngOnInit(): void {
    this.refresh();
  }

  /**
   * Call API REST to refresh website content.
   */
  refresh(): void {

    this.refreshProducts();
    this.refreshWishlist();

    this.activeSesion = this.authService.isUserLoggedIn();
  }

  updateContent(page: PageableProduct): void {

    let length = this.products.length;

    this.products.splice(length, 0, ...page.products);
    this.totalPages = page.totalPages;
  }

  refreshProducts(): void {
    let filter = ProductFilter.OneByName;
    this.productService.getProductWithFilter(filter, this.nextPage).subscribe(
      page => this.updateContent(page)
    )
  }

  refreshWishlist(): void {

    this.$wishlist = this.transactionService.getSpecialTransactionId(TransactionType.WISHLIST);
  }

  loadNextPage(): void {
    
    if (this.totalPages > this.nextPage) {
      this.nextPage++;   
      this.refresh();
    }
  }

}
