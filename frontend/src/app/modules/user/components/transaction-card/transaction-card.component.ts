import { Component, Input, OnInit } from '@angular/core';
import { Transaction } from '@app/shared/model';

@Component({
  selector: 'app-transaction-card',
  templateUrl: './transaction-card.component.html',
  styleUrls: ['./transaction-card.component.css']
})
export class TransactionCardComponent implements OnInit {

  @Input()
  transaction!: Transaction;
  totalPrice: number = 0;
  quantity: number = 0;

  constructor() { }

  ngOnInit(): void { 

  }



}
