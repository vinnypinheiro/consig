import {Component, Injectable, OnInit} from '@angular/core';
import * as Chartist from 'chartist';
import { ChartType, ChartEvent } from 'ng-chartist/dist/chartist.component';
import {CommonsForm} from "../../../commons-form";
import {Balanco} from "../balanco";
import {FilterData} from "../../../components/interfaces";
import {CommonsService} from "../../../commons-service";
import {ActivatedRoute, Router} from "@angular/router";
import {BalancoService} from "../balanco.service";

import {
    NgbDateStruct,
    NgbCalendar,
    NgbDatepickerI18n,
    NgbDateAdapter,
    NgbDateNativeAdapter
} from '@ng-bootstrap/ng-bootstrap';
import {FormBuilder} from "@angular/forms";
import {Subject} from "rxjs";
import {Associado} from "../../associado/associado";

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
    providers: [I18n, {provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n}, {provide: NgbDateAdapter, useClass: NgbDateNativeAdapter}]

})
export class BalancoViewComponent extends CommonsForm<Balanco>   implements OnInit {

    dtOptions: DataTables.Settings = {};
    dtTrigger: Subject<Associado> = new Subject();
    dtInstance: DataTables.Api;

    fromDate: NgbDateStruct;
    TotalAuxiliosFAM: number =  692;
    TotalEmprestato: number = 753336.03;
    balancoActive: any;
    totalparcelas: number;
    valortotalparcelas: number;

  constructor( apiService: BalancoService,
               route: ActivatedRoute,
               private fb: FormBuilder,
               calendar: NgbCalendar,
               router: Router) {
      super(apiService, route, router);
      this.fromDate = calendar.getToday();
  }

    entity: any;
    views: any;
    view: any;

  ngOnInit() {

      this.dtOptions = {
          pagingType: 'full_numbers',
          pageLength: 20,
          language: {
              emptyTable: "Nenhum registro encontrado",
              info: "Mostrando de _START_ até _END_ de _TOTAL_ registros",
              infoEmpty: "Mostrando 0 até 0 de 0 registros",
              infoFiltered: "(Filtrados de _MAX_ registros)",
              infoPostFix: "",
              thousands: ".",
              lengthMenu: "_MENU_ resultados por página",
              loadingRecords: "Carregando...",
              processing: "Processando...",
              zeroRecords: "Nenhum registro encontrado",
              search: "Pesquisar",
              paginate: {
                  next: "Próximo",
                  previous: "Anterior",
                  first: "Primeiro",
                  last: "Último"
              },
              aria: {
                  sortAscending: ": Ordenar colunas de forma ascendente",
                  sortDescending: ": Ordenar colunas de forma descendente"
              }
          }
      };

  }

    //Cliente reactive form
    associacaoForm = this.fb.group({
        associacaoId:  null,
      convenioid:  null,
      dataInicio:  null,
      dataFim:  null,

    });

    getByAssociacao(){

        this.apiService.getbyassociacao(this.associacaoForm.value).subscribe(response => {
            this.balancoActive = response;
            console.log("Balanco:   " + this.balancoActive);
            this.totalparcelas = this.balancoActive.length;
            this.dtInstance.destroy();
            this.dtTrigger.next();
            this.calculaValorTotalParcelas(this.balancoActive);


        });

    }

    getByPeriodo(){

        this.apiService.getbyassociacao(this.associacaoForm.value).subscribe(response => {
            this.balancoActive = response;
            this.totalparcelas = this.balancoActive.length;
            this.dtTrigger.next();
            this.calculaValorTotalParcelas(this.balancoActive);


        });

    }

    calculaValorTotalParcelas(totalparcelas){

        let valorTotal = [0];
        let valorInicial = 0;
        let valor;

        for (let entry of totalparcelas) {

            valorTotal.push(entry.vlrParcela);

        }

        var total = valorTotal.reduce(function(anterior, atual) {
            return Number(anterior) + Number(atual);
        });

        console.log(total)

        this.valortotalparcelas = total;
    }

    getByPeriodoDefault(){

        this.apiService.getbyassociacao(this.associacaoForm.value).subscribe(response => {
            this.balancoActive = response;
            this.dtInstance.destroy();
            this.dtTrigger.next();


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
