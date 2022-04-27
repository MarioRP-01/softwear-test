import { Component, Input, OnInit } from '@angular/core';
import { ProductInTransaction } from '@app/shared/model/product-in-transaction';

@Component({
  selector: 'app-product-row',
  templateUrl: './product-row.component.html',
  styleUrls: ['./product-row.component.css']
})
export class ProductRowComponent implements OnInit {

  @Input()
  product!: ProductInTransaction;

  constructor() { }

  ngOnInit(): void {
  }

}
