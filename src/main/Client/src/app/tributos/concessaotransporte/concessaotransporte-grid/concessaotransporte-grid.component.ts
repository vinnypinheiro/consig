import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {ConcessaoTransporte} from '../concessaotransporte'; 
import {ConcessaoTransporteService} from '../concessaotransporte.service'; 

@Component({ 
  selector: 'gov-concessaotransporte-grid', 
  templateUrl: './concessaotransporte-grid.component.html', 
  //styleUrls: ['./concessaotransporte-grid.component.css'] 
}) 
export class ConcessaoTransporteGridComponent extends CommonsGrid<ConcessaoTransporte> implements OnInit { 

     constructor(apiService: ConcessaoTransporteService, router: Router) { 
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
         filterData.order = 'trb_concessaotransporte.trb_veiculo_id, trb_concessaotransporte.linha ' 
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_concessaotransporte'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'trb_concessaotransporte' ; 
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('trb_veiculo_id','trb_veiculo_id','ENUM'); 
         retorno[1] = new FieldSearch('linha','linha','STRING'); 
         return retorno; 
     } 
getReportList():ReportGroup[]{ 
    return []; 
} 
}