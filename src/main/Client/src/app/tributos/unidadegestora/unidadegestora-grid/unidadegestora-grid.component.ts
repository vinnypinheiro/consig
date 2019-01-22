import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {UnidadeGestora} from '../unidadegestora'; 
import {UnidadeGestoraService} from '../unidadegestora.service'; 

@Component({ 
  selector: 'gov-unidadegestora-grid', 
  templateUrl: './unidadegestora-grid.component.html', 
  //styleUrls: ['./unidadegestora-grid.component.css'] 
}) 
export class UnidadeGestoraGridComponent extends CommonsGrid<UnidadeGestora> implements OnInit { 

     constructor(apiService: UnidadeGestoraService, router: Router) { 
         super(apiService, router); 
     } 

     ngOnInit() { 
         super.ngOnInit(); 
     } 

     onNavigateClick(filterData: FilterData): void { 
         this.loadByFilter(filterData); 
     } 

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         filterData.order = 'trb_unidadegestora.codigo, trb_unidadegestora.nome';
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_unidadegestora'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'trb_unidadegestora' ; 
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('codigo','codigo','STRING'); 
         retorno[1] = new FieldSearch('nome','nome','STRING'); 
         retorno[2] = new FieldSearch('cnpjcpf','cnpjcpf','STRING'); 
         retorno[3] = new FieldSearch('email','email','STRING'); 
         retorno[4] = new FieldSearch('telefone1','telefone1','STRING'); 
         retorno[5] = new FieldSearch('telefone2','telefone2','STRING'); 
         retorno[6] = new FieldSearch('datainscricao','datainscricao','DATE'); 
         retorno[7] = new FieldSearch('cep','cep','STRING'); 
         retorno[8] = new FieldSearch('trb_tipologradouro_id','trb_tipologradouro_id','ENUM'); 
         retorno[9] = new FieldSearch('logradouro','logradouro','STRING'); 
         retorno[10] = new FieldSearch('numero','numero','STRING'); 
         retorno[11] = new FieldSearch('complemento','complemento','STRING'); 
         retorno[12] = new FieldSearch('bairro','bairro','STRING'); 
         return retorno; 
     } 

     getReportList():ReportGroup[]{
        return [];
    }
}
