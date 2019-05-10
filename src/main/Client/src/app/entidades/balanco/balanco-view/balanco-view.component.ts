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

declare var require: any;


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
  selector: 'app-balanco-view',
  templateUrl: './balanco-view.component.html',
  styleUrls: ['./balanco-view.component.scss'],
    providers: [I18n]

})
export class BalancoViewComponent extends CommonsForm<Balanco>   implements OnInit {

    TotalAuxiliosFAM: number =  692;
    TotalEmprestato: number = 753336.03;

  constructor( apiService: BalancoService,route: ActivatedRoute,
               router: Router) {
      super(apiService, route, router);
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
