import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData, WhereClause} from '../../../components/interfaces';
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {Usuario} from '../usuario';
import {UsuarioService} from '../usuario.service';

@Component({ 
  selector: 'gov-usuario-grid', 
  templateUrl: './usuario-grid.component.html', 
  //styleUrls: ['./usuario-grid.component.css'] 
}) 
export class UsuarioGridComponent extends CommonsGrid<Usuario> implements OnInit {

     constructor(apiService: UsuarioService, router: Router) {
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
         filterData.order = 'usuario.email, usuario.nome '
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('usuario'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'usuario' ; 
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('email','email','STRING'); 
         retorno[1] = new FieldSearch('nome','nome','STRING'); 
         return retorno; 
     }

    getReportList():ReportGroup[]{
      return [];
    }

  buildRowFilter(tableName: string) {
    let filter = this.getDefaultFilter();

    filter.whereClauses.push(<WhereClause>{
      table: tableName,
      name:  'email',
      dataType: 'STRING',
      matchType: 'LIKE'
    });

    filter.whereClauses.push(<WhereClause>{
      table: tableName,
      name: 'nome',
      dataType: 'STRING',
      matchType: 'LIKE',
      whereType: 'OR'
    });

    return filter;
  }
}
