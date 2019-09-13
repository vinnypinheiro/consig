import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {ContaPagar} from '../contaPagar';
import {ContaPagarService} from '../contaPagar.service';

@Component({ 
  selector: 'gov-contaPagar-grid',
  templateUrl: './contaPagar-grid.component.html',
  //styleUrls: ['./contaPagar-grid.component.css']
}) 
export class ContaPagarGridComponent extends CommonsGrid<ContaPagar> implements OnInit {

     constructor(apiService: ContaPagarService, router: Router) {
         super(apiService, router); 
     } 

     ngOnInit() { 
        this.loadByFilter(this.getDefaultFilter());
     } 

     onNavigateClick(filterData: FilterData): void { 
         this.loadByFilter(filterData); 
     } 

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         filterData.order = 'contaPagar.bairro, contaPagar.banco, contaPagar.bancocontaPagar, contaPagar.bancoconta, contaPagar.cidade, contaPagar.cnpj, contaPagar.contato, contaPagar.cpf, contaPagar.datacadastro, contaPagar.email, contaPagar.email1, contaPagar.email2, contaPagar.emailcontato, contaPagar.endereco, contaPagar.fax, contaPagar.inscricaoestadual, contaPagar.inscricaomunicipal, contaPagar.nomefantasia, contaPagar.obs, contaPagar.razaosocial, contaPagar.site, contaPagar.telefone, contaPagar.telefone2 '
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_contaPagar');
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'contaPagar' ;
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('bairro','bairro','STRING'); 
         retorno[1] = new FieldSearch('banco','banco','STRING'); 
         retorno[2] = new FieldSearch('bancocontaPagar','bancocontaPagar','STRING');
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
