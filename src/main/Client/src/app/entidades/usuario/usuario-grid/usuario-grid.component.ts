import {Component, OnInit} from '@angular/core'; 
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
import {CommonsGrid} from '../../../commons-grid';
import {ReportGroup} from '../../../shared/report-group'; 
import {Usuario} from '../usuario'; 
import {UsuarioService} from '../usuario.service';

declare var $:any;


@Component({ 
  selector: 'app-usuario-grid', 
  templateUrl: './Usuario-grid.component.html', 
  //styleUrls: ['./Usuario-grid.component.css'] 
}) 
export class UsuarioGridComponent extends CommonsGrid<Usuario> implements OnInit {

    usuarios: {};

     constructor(apiService: UsuarioService, router: Router) { 
         super(apiService, router); 
     } 

     ngOnInit() { 

         this.loadByFilter(this.getDefaultFilter());
     } 

     loadByFilter(filterData: FilterData) {
        this.apiService.loadByFilter(filterData).subscribe(response => {
            this.activeBean  = response.content;
            this.usuarios = this.activeBean;

            console.log(this.activeBean);

        });
    }

     onNavigateClick(filterData: FilterData): void { 
         this.loadByFilter(filterData); 
     } 

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         filterData.order = 'Usuario.bairro, Usuario.banco, Usuario.bancoUsuario, Usuario.bancoconta, Usuario.cidade, Usuario.cnpj, Usuario.contato, Usuario.cpf, Usuario.datacadastro, Usuario.email, Usuario.email1, Usuario.email2, Usuario.emailcontato, Usuario.endereco, Usuario.fax, Usuario.inscricaoestadual, Usuario.inscricaomunicipal, Usuario.nomefantasia, Usuario.obs, Usuario.razaosocial, Usuario.site, Usuario.telefone, Usuario.telefone2 ' 
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_Usuario'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'Usuario' ; 
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('bairro','bairro','STRING'); 
         retorno[1] = new FieldSearch('banco','banco','STRING'); 
         retorno[2] = new FieldSearch('bancoUsuario','bancoUsuario','STRING'); 
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
    $('#usuariotable').DataTable({
      pageLength: 10,
      fixedHeader: true,
      responsive: true,
      "sDom": 'rtip',
      columnDefs: [{
        targets: 'no-sort',
        orderable: false
      }]
    });

    var table = $('#usuariotable').DataTable();
    $('#key-search').on('keyup', function() {
      table.search(this.value).draw();
    });
    $('#type-filter').on('change', function() {
      table.column(4).search($(this).val()).draw();
    });
  }


}
