import { Component, Input, OnInit } from '@angular/core';
import { TransactionService } from '@app/core/api/transaction.service';
import { Product } from '@app/shared/model/product';

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
