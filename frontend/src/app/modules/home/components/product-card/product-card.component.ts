import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Observable, map } from 'rxjs';

import { TransactionService } from '@app/core/api';

import { Transaction, Product as ProductInterface} from '@app/shared/model';
import { TransactionSpecialType } from '@app/shared/data-type';
import { Product } from '@app/shared/classes'


@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product!: ProductInterface;

  @Input()
  $wishlist!: Observable<Transaction>;

  @Input()
  activeSesion!: boolean;

  @Output()
  refreshWishlist = new EventEmitter<void>();

  public isPresent!: boolean;

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {

    this.refreshIsPresent();
  }

  createObjectProduct(productInterface: ProductInterface): Product {

    let product: Product = new Product(
      productInterface.id,
      productInterface.name,
      productInterface.description,
      productInterface.price,
      productInterface.size,
      productInterface.images
    );

    return product;
  }

  refreshIsPresent(): void {
    this.$wishlist.subscribe(
      transaction => {

        let product: Product = this.createObjectProduct(this.product);

        this.isPresent = product.containsProduct(transaction.products);
      },
      error => {
        this.isPresent = false;
        console.log(error);
      }
    )
  }

  addToWishlist() {

    this.isPresent = true;
    this.transactionService.addProductByAmountToMyTransaction(this.product.id, TransactionSpecialType.WISHLIST, 1).subscribe(
      error => {
        console.log(error);
      }
    )
  }

  removeFromWishlist() {

    this.isPresent = false;

    this.transactionService.deleteProductByAmountFromMyTransaction(this.product.id, TransactionSpecialType.WISHLIST, 1).subscribe(
      error => {
        console.log(error);
      }
    );
  }

  turnWishlist(): void {

    if (this.isPresent) {
      this.removeFromWishlist();

    } else {
      this.addToWishlist();

    }
    this.refreshWishlist.emit();
  }

}
