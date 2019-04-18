import { Component, OnInit, Injectable } from '@angular/core';

//Commons Imports
import {ActivatedRoute, Router} from "@angular/router";
import { CommonsService } from "../../../commons-service";
import {Operation} from "../../../utils/utils";
import {FilterData} from "../../../components/interfaces";
import { CommonsForm } from "../../../commons-form";
import {Associado} from "../associado";
import {AssociadoService} from "../associado.service";

import { NgbModal, ModalDismissReasons, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

//react form
import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';


//imports de entidades
import { AssociacaoService} from "../../associacao/associacao.service";
import {Associacao} from "../../associacao/associacao";
import { CorrespondenteService} from "../../correspondente/correspondente.service";
import {Correspondente} from "../../correspondente/correspondente";

import { NgbDateStruct, NgbCalendar, NgbDatepickerI18n } from '@ng-bootstrap/ng-bootstrap';
import {Convenio} from "../../convenio/convenio";

const I18N_VALUES = {
    'pt': {
        weekdays: ['Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sa', 'Do'],
        months: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    }
    // other languages you would support
};
declare var extenso: any;

// service imprt auxilio
import { AuxilioService } from "../../auxilio/auxilio.service";
import {VerbaDesconto} from "../../verbadesconto/verbadesconto";
import {Parcela} from "../../parcela/parcela";
import {Auxilio} from "../../auxilio/auxilio";
import {ParcelaService} from "../../parcela/parcela.service";
import {Subject} from "rxjs";

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

@Component({
  selector: 'app-associado-view',
  templateUrl: './associado-view.component.html',
  styleUrls: ['./associado-view.component.css'],
    providers: [I18n, {provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n}]
})
export class AssociadoViewComponent extends CommonsForm<Associado>   implements OnInit  {
    model: NgbDateStruct;

    dtOptions: DataTables.Settings = {};
    dtTrigger: Subject<Auxilio> = new Subject();

    entity: any;
    associacao: Associacao [];
    correspondente: Correspondente [];
    convenioList: Convenio[];
    verbaDescontoList: VerbaDesconto[];

    fromDate: NgbDateStruct;

    parcelaRow: Parcela;

    totalAuxilio: number;

    totalAssociaods: number;


    constructor(
        private parcelaService: ParcelaService,
        private auxilioService: AuxilioService,
        calendar: NgbCalendar,
        private fb: FormBuilder,
        private correspondenteService: CorrespondenteService,
        private associacaoservice: AssociacaoService,
        private modalService: NgbModal,
        apiService: AssociadoService,
        route: ActivatedRoute,
        router: Router
    ) {
        super(apiService, route, router);
        this.fromDate = calendar.getToday();
    }

  ngOnInit() {

        this.vlrparcela = 0;

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
                  this.calculaValorTotalAuxilio(this.entity);
                  this.dtTrigger.next();
              });
          }
      });

      this.associacaoservice.loadByFilter(this.getDefaultFilter()).subscribe(response => {
          this.associacao = response.content;
          console.log(this.associacao);
      });

      this.correspondenteService.loadByFilter(this.getDefaultFilter()).subscribe(response => {
          this.correspondente = response.content;
      });

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

  copyCPF(){



  }

  auxilioActive: Auxilio;
  gestaoParcela(row,auxilio, parcelaModal){
      this.auxilioActive = auxilio;
        this.parcelaRow = row;
      this.openLg(parcelaModal);
  }

    qtdparcela: number;
    vlrauxilio: number;
    vlrparcela: number;
    porcentagem: number;



    calculaVlrParcela(){

        this.vlrauxilio = this.activeForm.controls.vlrauxilio.value;
        this.qtdparcela = this.activeForm.controls.qtdparcelas.value;
        this.porcentagem =  this.activeForm.controls.porcentagem.value;
        this.vlrparcela = this.vlrauxilio * (this.porcentagem/100);

        this.porcentagem = this.porcentagem/100

        this.vlrparcela = ( this.vlrauxilio* this.porcentagem)/(1 - Math.pow(1/(1+ this.porcentagem),this.qtdparcela))
        this.vlrparcela = Math.round( this.vlrparcela*100)/100

        this.activeForm.patchValue(

            {
                vlrparcelas: this.vlrparcela,
                vlrauxextenso: extenso(this.vlrauxilio, {real: true})

            });

    }

    setValorExtenso(){

        this.vlrauxilio = this.activeForm.controls.vlrauxilio.value;
        this.activeForm.patchValue(

            {
                vlrauxextenso: extenso(this.vlrauxilio, {real: true}),

            });
        if( this.qtdparcela){
            this.calculaVlrParcela();
        }
    }

    calculaValorTotalAuxilio(entity){

        let valorTotal = [0];
        let valorInicial = 0;
        let valor;

        for (let entry of entity.auxilioList) {

            valorTotal.push(entry.vlrauxilio);

        }

        var total = valorTotal.reduce(function(anterior, atual) {
            return Number(anterior) + Number(atual);
        });

        console.log(total)

        this.totalAuxilio = total;
    }

    //Cliente reactive form
    parcelaForm = this.fb.group({

        id: null,
        datapagamento:  null,
        status:  null,
        valorpago: null

    });


    //Auxilio reactive form
    activeForm = this.fb.group({


        data: null,
        numeroproposta:  null,
        vlrauxilio:  null,
        porcentagem: null,
        vlrauxextenso:  null,
        qtdparcelas:  null,
        vlrparcelas:  null,
        convenio_id:  null,
        associacao_id:  null,
        parcela_id:  null,
        associado_id:  null,
        correspondente_id: null,
        dataContrato: null,
        verbadesconto_id: null


    });

    setConvenioList(event, a){
        this.convenioList = this.activeForm.controls.associacao_id.value.convenioList;
    }

    setVerbaDescontoList(event, a){
        this.verbaDescontoList = this.activeForm.controls.convenio_id.value.verbadescontolist;
    }

    openLg(content) {
        this.modalService.open(content, { size: 'lg' });
    }

    closeLg(result: any){
        this.auxilioActive = null;
        this.modalService.dismissAll();
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

    saveAuxilio(){

        let dia =  this.activeForm.controls.dataContrato.value.day;
        let mes =   this.activeForm.controls.dataContrato.value.month;
        let ano =  this.activeForm.controls.dataContrato.value.year;

        var d = new Date(mes +'/'+ dia +'/'+ano);

        this.activeForm.patchValue(
            {
                associado_id: this.entity,
                dataContrato: d,
                numeroproposta: this.entity.cpf + "."+ d.getFullYear(),

            }
        );

        this.auxilioService.save(this.activeForm.value).subscribe(response => {
            console.log(response);
            this.closeLg('Close');
            this.ngOnInit();
        });
    }

    atualizaParcela(){

        let dia =  this.parcelaForm.controls.datapagamento.value.day;
        let mes =   this.parcelaForm.controls.datapagamento.value.month;
        let ano =  this.parcelaForm.controls.datapagamento.value.year;

        var d = new Date(mes +'/'+ dia +'/'+ano);

        this.parcelaForm.patchValue(
            {

                id: this.auxilioActive.id,
                datapagamento:  d,
                status:  "PAGO",

            }
        );

        this.parcelaService.save(this.parcelaForm.value).subscribe(response => {
            console.log(response);
            this.closeLg('Close');
        });
    }

}
