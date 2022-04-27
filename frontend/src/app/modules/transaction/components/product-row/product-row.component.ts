import { Component, Input, OnInit } from '@angular/core';
import { TransactionService } from '@app/core/api';
import { TransactionSpecialType } from '@app/shared/data-type';
import { ProductInTransaction } from '@app/shared/model/product-in-transaction';

@Component({
  selector: 'app-product-row',
  templateUrl: './product-row.component.html',
  styleUrls: ['./product-row.component.css']
})
export class ProductRowComponent implements OnInit {

  @Input()
  product!: ProductInTransaction;

  @Input()
  index!: number;

  cart: TransactionSpecialType = TransactionSpecialType.CART;

  constructor(
    private transactionService: TransactionService
  ) { }

  ngOnInit(): void {
  }

  addProduct() {

    this.product.quantity++
    this.transactionService.addProductByAmountToMyTransaction(this.product.id, this.cart, 1).subscribe(
      error => {
        console.log(error);
      }
    )
  }

  deleteProduct() {

    this.product.quantity--
    this.transactionService.deleteProductByAmountFromMyTransaction(this.product.id, this.cart, 1).subscribe(
      error => {
        console.log(error);
      }
    );
  }

  deleteAllOfProduct() {

    this.product.quantity = 0;
    this.transactionService.deleteProductFromMyTransaction(this.product.id, this.cart).subscribe(
      error => {
        console.log(error);
      }
    )
  }

}
