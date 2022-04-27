import { Component, OnInit } from '@angular/core';
import { TransactionService } from '@app/core/api';
import { TransactionType } from '@app/shared/data-type';
import { PageableTransaction, Transaction } from '@app/shared/model';

@Component({
  selector: 'app-purchase-history',
  templateUrl: './purchase-history.component.html',
  styleUrls: ['./purchase-history.component.css']
})
export class PurchaseHistoryComponent implements OnInit {

  public processedType: TransactionType =  TransactionType.PROCESSED;

  public transactions: Transaction[] = []
  public totalPages: number = 0;
  public nextPage: number = 1;
 

  constructor(
    private transactionService: TransactionService
  ) { }

  ngOnInit(): void {

    this.refreshTransactios();
  }

  refreshTransactios(): void {

    this.transactionService.getMyTransactionsByType(this.processedType, this.nextPage).subscribe(
      result => this.loadPage(result)
    )
  }

  loadPage(page: PageableTransaction) {

    let length: number = this.transactions.length;

    this.transactions.splice(length, 0, ...page.transactions);
    this.totalPages = page.totalPages;
  }

  loadNextPage(){

    if (this.totalPages > this.nextPage) {

      this.nextPage++;   
      this.refreshTransactios();
    }
  }

}
