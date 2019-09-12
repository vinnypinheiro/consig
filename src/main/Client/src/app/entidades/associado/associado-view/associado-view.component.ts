import {Component, OnInit, Injectable, ViewEncapsulation} from '@angular/core';

//Commons Imports
import {ActivatedRoute, Router} from "@angular/router";
import { CommonsService } from "../../../commons-service";
import {Operation} from "../../../utils/utils";
import {FilterData} from "../../../components/interfaces";
import { CommonsForm } from "../../../commons-form";
import {Associado} from "../associado";
import {AssociadoService} from "../associado.service";

import { NgbModal, ModalDismissReasons, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import {OcorrenciaGridComponent} from "../../ocorrencia/ocorrencia-grid/ocorrencia-grid.component";


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
import {Associacaolink} from "../../associacaolink/associacaolink";
import {AssociacaolinkService} from "../../associacaolink/associacaolink.service";
//import {ConsultaCepService} from "../../../shared/consulta-cep.service";

@Injectable()
export class I18n {
  language = 'pt';
}

// Define custom service providing the months and weekdays translations
@Injectable()
export class CustomDatepickerI18n extends NgbDatepickerI18n {

  associado: any;

  constructor(private _i18n: I18n,  private modalService: NgbModal,) {
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
  encapsulation : ViewEncapsulation.None,
  providers: [I18n, {provide: NgbDatepickerI18n, useClass: CustomDatepickerI18n}]
})
export class AssociadoViewComponent extends CommonsForm<Associado>   implements OnInit  {
  model: NgbDateStruct;

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<Auxilio> = new Subject();

  entity: any;
  associacao: Associacao [];
  associado: Associado ;
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
    private associacaoLinkService: AssociacaolinkService,
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

    this.vlrparcela = 0.0;

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

          this.associadoForm.patchValue({
            id: this.activeBean.id,

            nome:  this.activeBean.nome,
            cpf:   this.activeBean.cpf,
            cep:   this.activeBean.cep,
            bairro:   this.activeBean.bairro,
            cidade:   this.activeBean.cidade,
            uf:   this.activeBean.uf,
            matricula:    this.activeBean.matricula,
            situacao:   this.activeBean.situacao,
            lotacao:    this.activeBean.lotacao,
            cargo:    this.activeBean.cargo,
            orgao:    this.activeBean.orgao,
            telefone:   this.activeBean.telefone,
            email:  this.activeBean.email,
            endereco:   this.activeBean.endereco,
            municipio:   this.activeBean.municipio,
            datacadastro:   this.activeBean.datacadastro,
            banco:   this.activeBean.banco,
            agencia:    this.activeBean.agencia,
            tipoconta:  this.activeBean.tipoconta,
            conta:   this.activeBean.conta,
            vlrmensalidade:   this.activeBean.vlrmensalidade,
            correspondente_id:  this.activeBean.correspondente_id,
            associacao1: this.activeBean.associacao1,
            associacao2: this.activeBean.associacao2,
            rg: this.activeBean.rg,
            obs: this.activeBean.obs

          });

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
      pageLength: 5,
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

  //Associado reactive form
  associadoForm = this.fb.group({

    id:   null,
    nome:   null,
    cpf:   null,
    matricula:    null,
    cidade:  null,
    bairro:   null,
    uf:   null,
    cep:  null,
    situacao:    null,
    lotacao:     null,
    cargo:    null,
    telefone:   null,
    email:    null,
    endereco:   null,
    estado:    null,
    municipio:    null,
    datacadastro:   null,
    banco:    null,
    agencia:    null,
    tipoconta:   null,
    conta:   null,
    correspondente_id:  null,
    verbadesconto: null,
    vlrmensalidade: null,
    orgao: null,
    associacaoLink:null,
    associacao1: null,
    associacao2: null,
    rg:null,
    obs: null


  });

  //Associado reactive form
  ocorrenciaForm = this.fb.group({

    id:   null,
    data:   Date,
    descricao:   null,
    observacao:   null,

  });

  openModal() {

    const modalOcorrencia = this.modalService.open(OcorrenciaGridComponent, {
      size: 'lg'
    });

    modalOcorrencia.componentInstance.activeEntity = this.entity;

  }


  saveAssociado() {
    // TODO: Use EventEmitter with form value
    console.warn(this.activeForm.value);
    this.activeBean = this.associadoForm.value;
    this.save();
    //this.apiService.save(this.clienteForm.value);
  }


  copyCPF(){



  }


  auxilioActive: Auxilio;
  gestaoParcela(row,auxilio, parcelaModal){
    this.auxilioActive = auxilio;
    this.parcelaRow = row;
    this.openLg(parcelaModal);
  }


  gestaoAuxilio(auxilio, auxilioModal){
    this.auxilioActive = auxilio;
    console.log("Auxilio Ativo: " + this.auxilioActive);

    this.calculaDesagio(auxilio.vlrparcelas, auxilio.qtdparcelasnaopagas, auxilio.porcentagem);


    this.refisForm.patchValue(

      {
        convenio_id:  auxilio.convenio_id,
        associacao_id: auxilio.associacao_id,
        associado_id:  auxilio.associado_id,
        correspondente_id:auxilio.correspondente_id,
        verbadesconto_id: auxilio.verbadesconto_id,


      });

    this.openModalAuxilio(auxilioModal);
  }

  qtdparcela: number;
  vlrauxilio: number;
  vlrparcela: number;
  porcentagem: number;
  totalaberto: number;
  liquidoareceber: number;
  getQtdparcelasnaopagas: number;

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

  calculaDesagio(vlrparcela, qtdparcelasnaopagas, porcentagem){

    if ( porcentagem == 4 ){
      porcentagem = 2.8
    }

    if (porcentagem == 5){
      porcentagem = 3.5
    }

    if(porcentagem == 6 ){
      porcentagem = 4.2
    }

    if ( porcentagem == 7) {
      4.9
    }

    //var pmt = numericVal(document.calcform.pmt.value);


    // var n   = numericVal(document.calcform.n.value);


    //var i  = numericVal(document.calcform.i.value)/100;

    porcentagem = porcentagem/100
    //engarcos contratuais
    // var pen  = numericVal(document.calcform.pen.value);



    this.totalaberto =  vlrparcela * ((1- Math.pow((1 + porcentagem),- qtdparcelasnaopagas)) / porcentagem );



  }

  calculaVlrParcelaRefis(){

    this.vlrparcela = this.refisForm.controls.vlrparcelas.value;
    this.qtdparcela = this.refisForm.controls.qtdparcelas.value;
    this.porcentagem =  this.refisForm.controls.porcentagem.value;


    this.porcentagem = this.porcentagem/100;


    this.vlrauxilio = (this.vlrparcela*(1 - Math.pow(1/(1+this.porcentagem),this.qtdparcela))/this.porcentagem)
    this.vlrauxilio = Math.round(this.vlrauxilio*100)/100


    // this.vlrparcela = ( this.vlrauxilio* this.porcentagem)/(1 - Math.pow(1/(1+ this.porcentagem),this.qtdparcela))
    // this.vlrparcela = Math.round( this.vlrparcela*100)/100

    this.liquidoareceber = this.vlrauxilio - this.totalaberto;

    var vlraux;

    vlraux = this.vlrauxilio.toString().replace('.', ',');


    this.refisForm.patchValue(

      {
        vlrauxilio: this.vlrauxilio,
        vlrauxextenso: vlraux,
        vlrliquidoliberado: this.liquidoareceber,
        vlrliquidadorefi: this.totalaberto

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

  quitarParcelas(auxilio, qtd){

    this.auxilioService.quitarparcelas(auxilio, qtd.value).subscribe(response => {
      console.log(response);
      this.closeLg('Close');
      this.ngOnInit();
    });


  }


  calculaValorTotalAuxilio(entity){

    let valorTotal = [0];
    let valorInicial = 0;
    let valor;

    for (let entry of entity.auxilioList) {

      valorTotal.push(entry.vlrliquidoliberado);

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
    verbadesconto_id: null,
    arquivo: null


  });

  //REFIS reactive form
  refisForm = this.fb.group({


    data: null,
    vlrliquidoliberado: null,
    qtdparcelasnaopagas: null,
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
    verbadesconto_id: null,
    arquivo: null,
    totalaberto: null,
    vlrliquidadorefi: null


  });

  setConvenioList(event, a){
    this.convenioList = this.activeForm.controls.associacao_id.value.convenioList;
  }


  setVerbaDescontoList(event, a){
    this.verbaDescontoList = this.activeForm.controls.convenio_id.value.verbadescontolist;
  }

  setConvenioListRefis(event, a){
    this.convenioList = this.refisForm.controls.associacao_id.value.convenioList;
  }


  setVerbaDescontoListRefis(event, a){
    this.verbaDescontoList = this.refisForm.controls.convenio_id.value.verbadescontolist;
  }

  openLg(content) {
    this.modalService.open(content, { size: 'lg' });
  }

  openModalAuxilio(content) {
    this.modalService.open(content, { size: 'lg',  windowClass: 'auxilio-modal' });
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

  saveAuxilioRefis(id){

    let dia =  this.refisForm.controls.dataContrato.value.day;
    let mes =   this.refisForm.controls.dataContrato.value.month;
    let ano =  this.refisForm.controls.dataContrato.value.year;

    var d = new Date(mes +'/'+ dia +'/'+ano);

    this.refisForm.patchValue(
      {
        associado_id: this.entity,
        totalpago: 0,
        dataContrato: d,

      }
    );

    this.auxilioService.saverefis(this.refisForm.value, id ).subscribe(response => {
      console.log(response);
      this.closeLg('Close');
      this.ngOnInit();
    });
  }

  saveAuxilio(){

    let dia =  this.activeForm.controls.dataContrato.value.day;
    let mes =   this.activeForm.controls.dataContrato.value.month;
    let ano =  this.activeForm.controls.dataContrato.value.year;

    var d = new Date(mes +'/'+ dia +'/'+ano);

    this.activeForm.patchValue(
      {
        associado_id: this.entity,
        totalpago: 0,
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

