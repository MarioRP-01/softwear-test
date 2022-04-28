import { Component, OnInit } from '@angular/core';
import { TransactionService } from '@app/core/api';
import { TransactionSpecialType } from '@app/shared/data-type';
import { ProductInTransaction, Transaction } from '@app/shared/model';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  wishlistType = TransactionSpecialType.WISHLIST;
  cartType = TransactionSpecialType.CART;

  wishlist!: Transaction;
  products: ProductInTransaction[] = [];

  constructor(
    private transactionService: TransactionService
  ) { }

  ngOnInit(): void {

    this.refreshWishlist();
  }

  refreshWishlist(): void {

    this.transactionService.getMySpecialTransactionByType(this.wishlistType).subscribe(
      (result: Transaction) => {
        this.wishlist = result;
        this.loadPage();

      }, error => console.log(error)
    )
  }

  loadPage() {

    this.wishlist.products.forEach(
      product => {

        let productInTransaction = {
          id: product.id,
          name: product.name,
          size: product.size,
          price: product.price,
          quantity: 1
        }

        let position = this.productIsPresent(productInTransaction) 
        if ( position == -1) {
          this.products.push(productInTransaction)

        } else {
          this.products[position].quantity++

        }
      }
    )
  }

  private productIsPresent(
    product: ProductInTransaction): number {

    let isContained = false;

    let productIndex = 0;
    if (this.products.length !== productIndex) {

      while ((productIndex < this.products.length) && !isContained) {

        isContained = this.products[productIndex].id == product.id;
        if (!isContained)
          productIndex++
      }

    }

    if (!isContained)
      productIndex = -1

    return productIndex
  }


  addToCart(index: number) {

    this.products[index].quantity = 0

    this.transactionService.addProductByAmountToMyTransaction(this.products[index].id, this.cartType, 1).subscribe(
      error => console.log(error)
    )

    this.transactionService.deleteProductFromMyTransaction(this.products[index].id, this.wishlistType).subscribe(
      error => console.log(error)
    )
  }

  deleteFromWishlist(index: number) {

    this.products[index].quantity = 0

    this.transactionService.deleteProductFromMyTransaction(this.products[index].id, this.wishlistType).subscribe(
      error => console.log(error)
    )
  }

  emptyWishlist() {

    this.products = []
    this.transactionService.deleteAllProductsFromMyTransaction(this.wishlistType).subscribe(
      error => console.log(error)
    )
  }
}
