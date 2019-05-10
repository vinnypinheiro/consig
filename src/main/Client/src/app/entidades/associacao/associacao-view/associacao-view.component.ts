import {Component, Injectable, OnInit} from '@angular/core';
import * as Chartist from 'chartist';
import { ChartType, ChartEvent } from 'ng-chartist/dist/chartist.component';
import {Operation} from "../../../utils/utils";
import {CommonsForm} from "../../../commons-form";
import {Associacao} from "../associacao";
import {FilterData} from "../../../components/interfaces";
import {CommonsService} from "../../../commons-service";
import {ActivatedRoute, Router} from "@angular/router";
import {AssociacaoService} from "../associacao.service";
import {NgbDatepickerI18n} from "@ng-bootstrap/ng-bootstrap";
import {CustomDatepickerI18n} from "../../associado/associado-view/associado-view.component";

declare var require: any;

const data: any = require('./data.json');

interface Chart {
    type: ChartType;
    data: Chartist.IChartistData;
    options?: any;
    responsiveOptions?: any;
    events?: ChartEvent;
}

@Injectable()
export class I18n {
    language = 'pt';
}

@Component({
  selector: 'app-associacao-view',
  templateUrl: './associacao-view.component.html',
  styleUrls: ['./associacao-view.component.scss'],
    providers: [I18n]

})
export class AssociacaoViewComponent extends CommonsForm<Associacao>   implements OnInit {

    TotalAuxiliosFAM: number =  692;
    TotalEmprestato: number = 753336.03;

  constructor( apiService: AssociacaoService,route: ActivatedRoute,
               router: Router) {
      super(apiService, route, router);
  }

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
    entity: any;
    views: any;
    view: any;

  ngOnInit() {

      this.beanSubscribe = this.route.params.subscribe(params => {
          this.beanId = params['id'];
          if (this.beanId === "novo") {
              this.beanId = "";
              this.operation = Operation.CREATE;
          } else {
              this.operation = Operation.SELECT;
              this.apiService.findById(this.beanId).subscribe(response => {
                  this.activeBean = (<any>response);
                  this.entity = this.activeBean;
                  console.log(this.entity);

              });

              this.apiService.getView(this.beanId).subscribe(response => {
                  this.views = (<any>response);
                  this.view =  this.views[0];
                  console.log(this.view);

              });
          }
      });




  }

    getDeLookupFilter(lookupValue: any): FilterData {
        switch (lookupValue.name) {
            case 'uf': {
                return this.buildLookupFilter('uf');
            }

            default: {
                console.log('filtro não disponibilizado para ', lookupValue.name)
            }
        }
        return null;
    }

    getLookupService(lookupName: string): CommonsService<any> {
        switch (lookupName) {

            default: {
                console.log('serviço não disponibilizado para ', lookupName)
                return this.apiService;
            }
        }
    }

}
