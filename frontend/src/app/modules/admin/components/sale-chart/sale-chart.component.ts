import { Component, Input } from '@angular/core';
import { ChartData, ChartOptions, ChartType } from 'chart.js';
import { BaseChartDirective  } from 'ng2-charts';

import DataLabelsPlugin from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-sale-chart',
  templateUrl: './sale-chart.component.html',
  styleUrls: ['./sale-chart.component.css']
})
export class SaleChartComponent {
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
