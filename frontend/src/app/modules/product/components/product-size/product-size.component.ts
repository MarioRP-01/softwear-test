import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { TransactionService } from '@app/core/api';
import { ShopUser, Transaction } from '@app/shared/classes';
import { TransactionSpecialType } from '@app/shared/data-type';
import { Product } from '@app/shared/model';
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
  wishlist!: Transaction | null;

  isPresent!: boolean;

  faHeart = faHeart; 

  isInWishlist: any;

  amount: FormControl =  new FormControl(1, [
    Validators.required,
    Validators.min(1)
  ])

  cartType: TransactionSpecialType = TransactionSpecialType.CART;
  wishlistType: TransactionSpecialType = TransactionSpecialType.WISHLIST;

  constructor(
    private transactionService: TransactionService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    this.checkIfPresent()
  }

  checkIfPresent() {

  }

  addToCart() {

    this.transactionService.addProductByAmountToMyTransaction
      (this.product.id, this.cartType, this.amount.value);
  }

  addToWishlist() {

    this.transactionService.addProductByAmountToMyTransaction
      (this.product.id, this.wishlistType, 1)
  }

  isLogged() {
    return this.user != null;
  }

}
