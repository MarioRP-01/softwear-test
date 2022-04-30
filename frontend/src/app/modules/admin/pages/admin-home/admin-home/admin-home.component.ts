import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChartData } from 'chart.js';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  incomes!: ChartData<'bar'>;
  sales!: ChartData<'bar'>;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.incomes = this.route.snapshot.data['incomes']
    this.sales = this.route.snapshot.data['sales']
  }

}
