import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {Entidade} from '../entidade'; 
import {EntidadeService} from '../entidade.service'; 

@Component({ 
  selector: 'gov-entidade-grid', 
  templateUrl: './entidade-grid.component.html', 
  //styleUrls: ['./entidade-grid.component.css'] 
}) 
export class EntidadeGridComponent extends CommonsGrid<Entidade> implements OnInit { 

     constructor(apiService: EntidadeService, router: Router) { 
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
         filterData.order = 'entidade.codigo, entidade.nome, entidade.cnpj, entidade.cidade_id ' 
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_entidade'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'entidade' ; 
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('codigo','codigo','STRING'); 
         retorno[1] = new FieldSearch('nome','nome','STRING'); 
         retorno[2] = new FieldSearch('cnpj','cnpj','STRING'); 
         retorno[3] = new FieldSearch('cidade_id','cidade_id','NUMBER'); 
         return retorno; 
     } 
getReportList():ReportGroup[]{ 
    return []; 
} 
}