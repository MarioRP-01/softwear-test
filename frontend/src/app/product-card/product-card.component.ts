import { Component, Input, OnInit } from '@angular/core';
import { TransactionService } from '../api/transaction.service';
import { TransactionType } from '../model/data/transaction-type';
import { Product } from '../model/product';
import { Transaction } from '../model/transaction';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product!: Product;

  @Input()
  wishlistId!: number;

  isInWishlist: boolean = false;

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
  }

  turnWishlist(): void {

  }

}
