import { Component, Input, OnInit } from '@angular/core';
import { Transaction } from '@app/shared/model';
import { ProductInTransaction } from '@app/shared/model/product-in-transaction';

@Component({
  selector: 'app-transaction-card',
  templateUrl: './transaction-card.component.html',
  styleUrls: ['./transaction-card.component.css']
})
export class TransactionCardComponent implements OnInit {

  @Input()
  transaction!: Transaction;

  products: ProductInTransaction[] = [];

  totalPrice: number = 0;

  constructor() { }

  ngOnInit(): void { 

    this.generateProductsInTransaction();
    this.calculateTotalPrice();
  }  
    
  generateProductsInTransaction() {

    this.transaction.products.forEach(
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

    productIsPresent(
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

  calculateTotalPrice() {
    for (const product of this.transaction.products) {

      this.totalPrice += product.price;
    }
  }
  
}
