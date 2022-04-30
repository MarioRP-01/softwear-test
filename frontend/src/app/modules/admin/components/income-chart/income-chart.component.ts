import { Component, Input } from '@angular/core';
import { ChartData, ChartOptions, ChartType } from 'chart.js';

import DataLabelsPlugin from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-income-chart',
  templateUrl: './income-chart.component.html',
  styleUrls: ['./income-chart.component.css'],
})
export class IncomeChartComponent {
  public barChartOptions: ChartOptions = 
  {
    responsive: true,
    scales: {
      x: {},
      y: {}
    },
    plugins: {
      legend: {
        display: true,
      },
      datalabels: {
        anchor: 'end',
        align: 'end'
      }
    }
  };

  public barChartType: ChartType = 'bar';
  public barChartPlugins = [DataLabelsPlugin];

  @Input()
  public barChartData!: ChartData<'bar'>;

  public barChartLegend = true;
  
  constructor() {}
}
