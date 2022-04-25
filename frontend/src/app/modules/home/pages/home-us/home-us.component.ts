import { Component, Input, OnInit } from '@angular/core';
import { map, Observable, shareReplay } from 'rxjs';

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
  public $wishlist!: Observable<Transaction>;

  public activeSesion!: boolean;

  @Input()
  type:string = '';


  

  constructor(
    private productService: ProductService,
    private transactionService: TransactionService,
    private authService: AuthService

    ) { }

  ngOnInit(): void {
    
    this.refreshProducts()
    this.refreshWishlist();

    this.authService.loadUser().pipe(shareReplay()).subscribe(
      response => {
        this.activeSesion = this.authService.isUserLoggedIn();

      },
      error => console.log(error)
    );
  }

  loadPage(page: PageableProduct) {

    let length = this.products.length;

    this.products.splice(length, 0, ...page.products);
    this.totalPages = page.totalPages;
  }

  refreshProducts(): void {

    this.productService.getProductWithFilter(ProductFilter.OneByName, this.nextPage).subscribe(
      page => this.loadPage(page)
    )
  }

  refreshWishlist(): void {

    this.$wishlist = this.transactionService.getMyTransactionByType(TransactionType.WISHLIST).pipe(shareReplay());
  }

  loadNextPage(): void {
    
    if (this.totalPages > this.nextPage) {
      this.nextPage++;   
      this.refreshProducts();
    }
  }

}
