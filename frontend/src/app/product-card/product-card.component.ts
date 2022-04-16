import { Component, Input, OnInit } from '@angular/core';
import { TransactionService } from '../api/transaction.service';
import { Product } from '../model/product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input()
  product!: Product;

  constructor(private transactionService: TransactionService) { }

  ngOnInit(): void {
  }

}
