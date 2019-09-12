import { Component, OnInit, AfterViewInit} from '@angular/core';

import {Operation} from "../../../utils/utils";
import {CommonsForm} from "../../../commons-form";
import {Associado} from "../../associado/associado";
import {Convenio} from "../convenio";
import {AssociacaoService} from "../../associacao/associacao.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FilterData} from "../../../components/interfaces";
import {CommonsService} from "../../../commons-service";
import {ConvenioService} from "../convenio.service";

declare var require: any;

const data: any = require('./data.json');


@Component({
  selector: 'app-associacao-view',
  templateUrl: './convenio-view.component.html',
  styleUrls: ['./convenio-view.component.scss']
})

export class ConvenioViewComponent extends CommonsForm<Convenio>  implements OnInit, AfterViewInit {

    constructor( apiService: ConvenioService,route: ActivatedRoute,
                 router: Router) {
        super(apiService, route, router);
    }



    ngAfterViewInit() {
      
    }
    entity: any;
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
