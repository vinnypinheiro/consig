import { Component, OnInit } from '@angular/core';

import {Correspondente} from "../correspondente";
import {CorrespondenteService} from "../correspondente.service";

//Commons Imports
import {ActivatedRoute, Router} from "@angular/router";
import { CommonsService } from "../../../commons-service";
import {Operation} from "../../../utils/utils";
import {FilterData} from "../../../components/interfaces";
import { CommonsForm } from "../../../commons-form";

@Component({
  selector: 'app-correspondente-view',
  templateUrl: './correspondente-view.component.html',
  styleUrls: ['./correspondente-view.component.css']
})
export class CorrespondenteViewComponent extends CommonsForm<Correspondente>   implements OnInit {

    entity: object = {};

  constructor(
      apiService: CorrespondenteService,
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
