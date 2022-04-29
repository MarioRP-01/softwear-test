import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { TransactionService } from '@app/core/api';
import { ShopUser, Transaction } from '@app/shared/classes';
import { Wishlist } from '@app/shared/classes/wishlist';
import { TransactionSpecialType } from '@app/shared/data-type';
import { Product, ProductInTransaction } from '@app/shared/model';
import { faHeart } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-product-size',
  templateUrl: './product-size.component.html',
  styleUrls: ['./product-size.component.css']
})
export class ProductSizeComponent implements OnInit {

  @Input()
  product!: Product;

  @Input()
  user!: ShopUser | null;

  @Input()
  wishlist!: Wishlist | null;

  isPresent?: boolean;

  faHeart = faHeart; 
  productInTransaction!: ProductInTransaction;
  isInWishlist: any;

  amount: FormControl =  new FormControl(1, [
    Validators.required,
    Validators.min(1)
  ])

  wishlistButton: any;

  cartType: TransactionSpecialType = TransactionSpecialType.CART;
  wishlistType: TransactionSpecialType = TransactionSpecialType.WISHLIST;

  constructor(
    private transactionService: TransactionService
  ) { 

    
  }

  ngOnInit(): void {

    this.productInTransaction = this.initProductInTransaction();
    this.checkIfPresentInWishlist();
    this.initWishlistButton();
  }

  initProductInTransaction(): ProductInTransaction {

    return {
      id: this.product.id,
      name: this.product.name,
      size: this.product.size,
      price: this.product.price,
      quantity: 1

    }
  }

  initWishlistButton(): any {

    return {
      'col-1': true,
      'btn': true,
      'btn-outline-dark': true,
      'p-1': true, 
      'mx-1': true,
      'bg-dark': this.isPresent,
      'text-white': this.isPresent
    }
  }

  checkIfPresentInWishlist() {

    this.isPresent = this.wishlist?.productIsPresent(this.productInTransaction) != -1
  }

  addToCart() {

    this.transactionService.addProductByAmountToMyTransaction
      (this.product.id, this.cartType, this.amount.value).subscribe(
        response => console.log("works!"),
        error => console.log(error)
      );
  }

  toogleWishlist() {

    if (this.wishlist !== null) {

      if (!this.isPresent) {
        this.addToWishlist();

      } else {
        this.removeFromWishlist();

      }

    }
  }

  private addToWishlist() {

    this.isPresent = true;
    this.transactionService.addProductByAmountToMyTransaction
      (this.product.id, this.wishlistType, 1).subscribe(
        response => console.log("works!")
      )
  }

  private removeFromWishlist() {

    this.isPresent = false;
    this.transactionService.deleteProductFromMyTransaction
      (this.product.id, this.wishlistType).subscribe(
        response => console.log("works!")
      )
  }

  isLogged() {
    return this.user != null;
  }

}
