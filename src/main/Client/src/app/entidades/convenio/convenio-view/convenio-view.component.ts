import { Component, OnInit, AfterViewInit} from '@angular/core';
import * as Chartist from 'chartist';
import { ChartType, ChartEvent } from 'ng-chartist/dist/chartist.component';

import * as c3 from 'c3';

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
  templateUrl: './convenio-view.component.html',
  styleUrls: ['./convenio-view.component.scss']
})
export class ConvenioViewComponent implements OnInit, AfterViewInit {

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

    ngAfterViewInit() {
        const chart = c3.generate({
            bindto: '.earningsbox',
            data: {
                columns: [
                    ['Site A', 5, 6, 3, 7, 9, 10, 14, 12, 11, 9, 8, 7, 10, 6, 12, 10, 8]
                ],
                type: 'area-spline'
            },
            axis: {
                y: {
                    show: false,
                    tick: {
                        count: 0,
                        outer: false
                    }
                },
                x: {
                    show: false
                }
            },
            padding: {
                top: 0,
                right: -8,
                bottom: -28,
                left: -8
            },
            point: {
                r: 0
            },
            legend: {
                hide: true
            },
            color: {
                pattern: ['#40c4ff', '#dadada', '#ff821c', '#7e74fb']
            }
        });
    }

  ngOnInit() {
  }

}
