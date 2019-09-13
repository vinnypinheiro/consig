import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
import {CommonsGrid} from '../../../commons-grid';
import {ReportGroup} from '../../../shared/report-group'; 
import {Setor} from '../setor';
import {SetorService} from '../setor.service';

declare var $:any;


@Component({ 
  selector: 'app-setor-grid',
  templateUrl: './Setor-grid.component.html',
  //styleUrls: ['./Setor-grid.component.css']
}) 
export class SetorGridComponent extends CommonsGrid<Setor> implements OnInit {

    setors: {};

     constructor(apiService: SetorService, router: Router) {
         super(apiService, router); 
     } 

     ngOnInit() { 

         this.loadByFilter(this.getDefaultFilter());
     } 

     loadByFilter(filterData: FilterData) {
        this.apiService.loadByFilter(filterData).subscribe(response => {
            this.activeBean  = response.content;
            this.setors = this.activeBean;

            console.log(this.activeBean);

        });
    }

     onNavigateClick(filterData: FilterData): void { 
         this.loadByFilter(filterData); 
     } 

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         filterData.order = 'Setor.bairro, Setor.banco, Setor.bancoSetor, Setor.bancoconta, Setor.cidade, Setor.cnpj, Setor.contato, Setor.cpf, Setor.datacadastro, Setor.email, Setor.email1, Setor.email2, Setor.emailcontato, Setor.endereco, Setor.fax, Setor.inscricaoestadual, Setor.inscricaomunicipal, Setor.nomefantasia, Setor.obs, Setor.razaosocial, Setor.site, Setor.telefone, Setor.telefone2 '
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_Setor');
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'Setor' ;
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('bairro','bairro','STRING'); 
         retorno[1] = new FieldSearch('banco','banco','STRING'); 
         retorno[2] = new FieldSearch('bancoSetor','bancoSetor','STRING');
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

  ngAfterViewInit() {
    $('#setortable').DataTable({
      pageLength: 10,
      fixedHeader: true,
      responsive: true,
      "sDom": 'rtip',
      columnDefs: [{
        targets: 'no-sort',
        orderable: false
      }]
    });

    var table = $('#setortable').DataTable();
    $('#key-search').on('keyup', function() {
      table.search(this.value).draw();
    });
    $('#type-filter').on('change', function() {
      table.column(4).search($(this).val()).draw();
    });
  }


}
