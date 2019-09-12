import {Component, Injectable, OnInit} from '@angular/core';
import {Operation} from "../../../utils/utils";
import {CommonsForm} from "../../../commons-form";
import {Associacao} from "../associacao";
import {FilterData} from "../../../components/interfaces";
import {CommonsService} from "../../../commons-service";
import {ActivatedRoute, Router} from "@angular/router";
import {AssociacaoService} from "../associacao.service";

declare var require: any;

const data: any = require('./data.json');



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
