import { Component, OnInit } from '@angular/core';

import {RelatorioAlba} from "../relatorioalba";
import {RelatorioalbaService} from "../relatorioalba.service";

//Commons Imports
import {ActivatedRoute, Router} from "@angular/router";
import { CommonsService } from "../../../commons-service";
import {Operation} from "../../../utils/utils";
import {FilterData} from "../../../components/interfaces";
import { CommonsForm } from "../../../commons-form";

@Component({
  selector: 'app-relatorioAlba-view',
  templateUrl: './relatorioAlba-view.component.html',
  styleUrls: ['./relatorioAlba-view.component.css']
})
export class RelatorioAlbaViewComponent extends CommonsForm<RelatorioAlba>   implements OnInit {

    entity: object = {};

  constructor(
      apiService: RelatorioalbaService,
      route: ActivatedRoute,
      router: Router
  ) {
      super(apiService, route, router);
  }

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
                  console.log(this.entity)


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
