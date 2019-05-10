import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {Lancamento} from '../lancamento'; 
import {LancamentoService} from '../lancamento.service';
import { Subject } from 'rxjs';
import {AuxilioService} from "../../auxilio/auxilio.service";

@Component({ 
  selector: 'gov-lancamento-grid', 
  templateUrl: './lancamento-grid.component.html', 
  //styleUrls: ['./lancamento-grid.component.css'] 
}) 
export class LancamentoGridComponent extends CommonsGrid<Lancamento> implements OnInit {

    dtOptions: DataTables.Settings = {};
    dtTrigger: Subject<Lancamento> = new Subject();

    totalLancamentos: number;
    auxilios: {} ;

     constructor(apiService: LancamentoService, router: Router) {
         super(apiService, router); 
     } 

     ngOnInit() { 

         this.apiService.loadByFilter(this.getDefaultFilter()).subscribe(response => {
             this.activeBean =  response.content;
             if(this.activeBean != null ){

                 this.dtTrigger.next();
             }

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

     onNavigateClick(filterData: FilterData): void { 
         this.loadByFilter(filterData); 
     } 

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         filterData.order = 'lancamento.bairro, lancamento.banco, lancamento.bancolancamento, lancamento.bancoconta, lancamento.cidade, lancamento.cnpj, lancamento.contato, lancamento.cpf, lancamento.datacadastro, lancamento.email, lancamento.email1, lancamento.email2, lancamento.emailcontato, lancamento.endereco, lancamento.fax, lancamento.inscricaoestadual, lancamento.inscricaomunicipal, lancamento.nomefantasia, lancamento.obs, lancamento.razaosocial, lancamento.site, lancamento.telefone, lancamento.telefone2 ' 
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_lancamento'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'lancamento' ; 
     }

     copiarTexto(cpfcopy) {
         cpfcopy.select();
         document.execCommand('copy');
         cpfcopy.setSelectionRange(0, 0);
    }


     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('bairro','bairro','STRING'); 
         retorno[1] = new FieldSearch('banco','banco','STRING'); 
         retorno[2] = new FieldSearch('bancolancamento','bancolancamento','STRING'); 
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