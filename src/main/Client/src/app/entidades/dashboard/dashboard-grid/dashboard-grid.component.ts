import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {Dashboard} from '../dashboard';
import {DashboardService} from '../dashboard.service';
import {FormBuilder} from "@angular/forms";

declare var extenso: any;


@Component({ 
  selector: 'gov-dashboard-grid',
  templateUrl: './dashboard-grid.component.html',
  //styleUrls: ['./dashboard-grid.component.css']
}) 
export class DashboardGridComponent extends CommonsGrid<Dashboard> implements OnInit {

     constructor(apiService: DashboardService,
                 private fb: FormBuilder,
                 router: Router) {
         super(apiService, router); 
     } 

     ngOnInit() { 
        this.loadByFilter(this.getDefaultFilter());
     }



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


    qtdparcela: number;
    vlrauxilio: number;
    vlrparcela: any;
    vlrparcelarefis: any;
    porcentagem: number;
    totalaberto: number;
    getQtdparcelasnaopagas: number;

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

    //REFIS reactive form
    refisForm = this.fb.group({


        data: null,
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
        totalaberto: null


    });

    calculaVlrParcelaRefis(){

        this.vlrparcela = this.refisForm.controls.vlrparcelas.value;
        this.qtdparcela = this.refisForm.controls.qtdparcelas.value;
        this.porcentagem =  this.refisForm.controls.porcentagem.value;


        this.porcentagem = this.porcentagem/100;


        this.vlrauxilio = (this.vlrparcela*(1 - Math.pow(1/(1+this.porcentagem),this.qtdparcela))/this.porcentagem)
        this.vlrauxilio = Math.round(this.vlrauxilio*100)/100


        // this.vlrparcela = ( this.vlrauxilio* this.porcentagem)/(1 - Math.pow(1/(1+ this.porcentagem),this.qtdparcela))
        // this.vlrparcela = Math.round( this.vlrparcela*100)/100

        var vlraux;

        vlraux = this.vlrauxilio.toString().replace('.', ',');


        this.refisForm.patchValue(

            {
                vlrauxilio: this.vlrauxilio,
                vlrauxextenso: extenso(vlraux, {real: true})

            });

    }












     onNavigateClick(filterData: FilterData): void { 
         this.loadByFilter(filterData); 
     } 

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         return filterData;
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_dashboard');
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'dashboard' ;
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('bairro','bairro','STRING'); 
         retorno[1] = new FieldSearch('banco','banco','STRING'); 
         retorno[2] = new FieldSearch('bancodashboard','bancodashboard','STRING');
         retorno[3] = new FieldSearch('bancoconta','bancoconta','STRING'); 
         retorno[4] = new FieldSearch('cidade','cidade','STRING'); 
         retorno[5] = new FieldSearch('cnpj','cnpj','STRING'); 
         retorno[6] = new FieldSearch('contato','contato','STRING'); 
         retorno[7] = new FieldSearch('cpf','cpf','STRING'); 
         retorno[8] = new FieldSearch('datacadastro','datacadastro','DATE'); 
         retorno[9] = new FieldSearch('email','email','STRING'); 
         retorno[10] = new FieldSearch('email1','email1','STRING'); 
         retorno[11] = new FieldSearch('email2','email2','STRING'); 
         retorno[12] = new FieldSearch('emailcontato','emailcontato','STRING'); 
         retorno[13] = new FieldSearch('endereco','endereco','STRING'); 
         retorno[14] = new FieldSearch('fax','fax','STRING'); 
         retorno[15] = new FieldSearch('inscricaoestadual','inscricaoestadual','STRING'); 
         retorno[16] = new FieldSearch('inscricaomunicipal','inscricaomunicipal','STRING'); 
         retorno[17] = new FieldSearch('nomefantasia','nomefantasia','STRING'); 
         retorno[18] = new FieldSearch('obs','obs','STRING'); 
         retorno[19] = new FieldSearch('razaosocial','razaosocial','STRING'); 
         retorno[20] = new FieldSearch('site','site','STRING'); 
         retorno[21] = new FieldSearch('telefone','telefone','STRING'); 
         retorno[22] = new FieldSearch('telefone2','telefone2','STRING'); 
         return retorno; 
     } 
getReportList():ReportGroup[]{ 
    return []; 
} 
}