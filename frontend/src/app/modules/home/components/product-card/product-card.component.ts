import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable, map } from 'rxjs';

import { TransactionService } from '@app/core/api';

import { Transaction, Product } from '@app/shared/model';


@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product!: Product;

  @Input()
  $wishlist!: Observable<Transaction>;

  @Input()
  activeSesion!: boolean;

  @Output()
  refresh = new EventEmitter<void>();

  public isPresent!: boolean;

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {

    this.refreshIsPresent();
  }

  refreshIsPresent(): void {
    this.$wishlist.pipe(map(
      transaction => this.isPresent = transaction.products.includes(this.product),
      (error: any) => this.isPresent = false
    ));
  }

  addToWishlist() {
    this.isPresent = true;

  }

  removeFromWishlist() {
    this.isPresent = false;

  }

  turnWishlist(): void {

    if (this.isPresent) {
      this.addToWishlist()

    } else {
      this.removeFromWishlist()

    }
    this.refresh.emit()
  }
  

}
