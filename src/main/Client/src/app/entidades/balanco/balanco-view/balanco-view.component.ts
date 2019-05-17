import {Component, Injectable, OnInit} from '@angular/core';
import * as Chartist from 'chartist';
import { ChartType, ChartEvent } from 'ng-chartist/dist/chartist.component';
import {Operation} from "../../../utils/utils";
import {CommonsForm} from "../../../commons-form";
import {Balanco} from "../balanco";
import {FilterData} from "../../../components/interfaces";
import {CommonsService} from "../../../commons-service";
import {ActivatedRoute, Router} from "@angular/router";
import {BalancoService} from "../balanco.service";

import { NgbDateStruct, NgbCalendar, NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';

const I18N_VALUES = {
    'pt': {
        weekdays: ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sa', 'Do'],
        months: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    }
    // other languages you would support
};

@Injectable()
export class I18n {
    language = 'pt';
}

// Define custom service providing the months and weekdays translations
@Injectable()
export class CustomDatepickerI18n extends NgbDatepickerI18n {

    constructor(private _i18n: I18n) {
        super();
    }

    getWeekdayShortName(weekday: number): string {
        return I18N_VALUES[this._i18n.language].weekdays[weekday - 1];
    }
    getMonthShortName(month: number): string {
        return I18N_VALUES[this._i18n.language].months[month - 1];
    }
    getMonthFullName(month: number): string {
        return this.getMonthShortName(month);
    }

    getDayAriaLabel(date: NgbDateStruct): string {
        return `${date.day}-${date.month}-${date.year}`;
    }
}

declare var require: any;

interface Chart {
    type: ChartType;
    data: Chartist.IChartistData;
    options?: any;
    responsiveOptions?: any;
    events?: ChartEvent;
}


@Component({
  selector: 'app-balanco-view',
  templateUrl: './balanco-view.component.html',
  styleUrls: ['./balanco-view.component.scss'],
    providers: [I18n, {provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n}]

})
export class BalancoViewComponent extends CommonsForm<Balanco>   implements OnInit {

    fromDate: NgbDateStruct;
    TotalAuxiliosFAM: number =  692;
    TotalEmprestato: number = 753336.03;

  constructor( apiService: BalancoService,route: ActivatedRoute,calendar: NgbCalendar,
               router: Router) {
      super(apiService, route, router);
      this.fromDate = calendar.getToday();
  }

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
