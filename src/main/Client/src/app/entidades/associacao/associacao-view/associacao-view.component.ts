import { Component, OnInit } from '@angular/core';
import * as Chartist from 'chartist';
import { ChartType, ChartEvent } from 'ng-chartist/dist/chartist.component';

declare var require: any;

const data: any = require('./data.json');

interface Chart {
    type: ChartType;
    data: Chartist.IChartistData;
    options?: any;
    responsiveOptions?: any;
    events?: ChartEvent;
}

@Component({
  selector: 'app-associacao-view',
  templateUrl: './associacao-view.component.html',
  styleUrls: ['./associacao-view.component.scss']
})
export class AssociacaoViewComponent implements OnInit {

  constructor() { }

    lineChart: Chart = {
        type: 'Line',
        data: data['Line'],
        options: {
            low: 0,
            high: 28,
            showArea: true,
            fullWidth: true,
            axisY: {
                onlyInteger: true,
                scaleMinSpace: 40,
                offset: 20,
                labelInterpolationFnc: function(value: number): string {
                    return value / 1 + 'k';
                }
            }
        }
    };


  ngOnInit() {


  }

}
