import { Component, OnInit } from '@angular/core';
import { TransactionService } from '@app/core/api';
import { TransactionSpecialType } from '@app/shared/data-type';
import { Transaction } from '@app/shared/model';
import { ProductInTransaction } from '@app/shared/model/product-in-transaction';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public cartType: TransactionSpecialType = TransactionSpecialType.CART;

  public cart!: Transaction;

  public products: ProductInTransaction[] = []
  public totalPrice: number = 0;
  public totalAmount: number = 0;

  constructor(
    private transactionService: TransactionService
  ) { }

  

  ngOnInit(): void {

    
  }

  refreshTransactions(): void {

    this.transactionService.getMySpecialTransactionByType(this.cartType).subscribe(
      (result: Transaction) => {
        this.cart = result;
        this.loadPage();
    })
  }

  private loadPage():void {
    
    this.generateProductInTransaction();
    this.calculateTotal();
  }

  generateProductInTransaction():void {

    this.cart.products.forEach(
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

  calculateTotal() {

    for (const product of this.cart.products) {

      this.totalPrice += product.price;
      this.totalPrice += 1
    }
  }

  emptyCart(): void {

    this.transactionService.deleteAllProductsFromMyTransaction(this.cartType).subscribe(
      response => console.log("works!")
    );
  }

}

