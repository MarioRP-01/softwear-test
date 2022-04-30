import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { ChartData } from 'chart.js';
import { catchError, map, Observable, of } from 'rxjs';
import { TransactionService } from '../api';

@Injectable({
  providedIn: 'root'
})
export class SalesResolver implements Resolve<ChartData<'bar'>> {

  constructor(
    private transactionService: TransactionService
  ) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ChartData<'bar'>> {

    const barChartData: ChartData<'bar'> = {
      labels: [],
      datasets: [
        {
          data: [],
          label: 'units'
        }
      ]
    }

    return this.transactionService.getStatics().pipe(map(
      response => {

        for (let data of response) {
          barChartData.labels?.push(data.productName);
          barChartData.datasets[0].data.push(data.sales)
        }

        return barChartData;
      }, catchError(
        err => of(barChartData)
      )
    ))
  }
}
