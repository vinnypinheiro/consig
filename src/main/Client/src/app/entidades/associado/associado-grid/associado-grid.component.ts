import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {Associado} from '../associado'; 
import {AssociadoService} from '../associado.service';
import { Subject } from 'rxjs';
import {DataTableDirective} from "angular-datatables";
import {FormBuilder} from "@angular/forms";

@Component({ 
  selector: 'gov-associado-grid', 
  templateUrl: './associado-grid.component.html', 
  //styleUrls: ['./associado-grid.component.css'] 
}) 
export class AssociadoGridComponent extends CommonsGrid<Associado> implements OnInit, AfterViewInit {

    dtOptions: DataTables.Settings = {};
    dtTrigger: Subject<Associado> = new Subject();

    @ViewChild(DataTableDirective)
    dtElement: DataTableDirective;

    totalAssociados: number;
    associaodos: any ;

     constructor(apiService: AssociadoService,
                 router: Router,
                 private fb: FormBuilder,) {
         super(apiService, router); 
     } 

     ngOnInit() { 

         // this.apiService.loadByFilter(this.getDefaultFilter()).subscribe(response => {
         //     this.activeBean =  response.content;
         //     if(this.activeBean != null ){
         //         this.associaodos = this.activeBean;
         //         console.log("teste")
         //         this.dtTrigger.next();
         //     }
         //
         // });


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
         filterData.order = 'associado.bairro, associado.banco, associado.bancoassociado, associado.bancoconta, associado.cidade, associado.cnpj, associado.contato, associado.cpf, associado.datacadastro, associado.email, associado.email1, associado.email2, associado.emailcontato, associado.endereco, associado.fax, associado.inscricaoestadual, associado.inscricaomunicipal, associado.nomefantasia, associado.obs, associado.razaosocial, associado.site, associado.telefone, associado.telefone2 ' 
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_associado'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'associado' ; 
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
         retorno[2] = new FieldSearch('bancoassociado','bancoassociado','STRING'); 
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

    rerender(): void {
        this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
            // Destroy the table first
            dtInstance.destroy();
            // Call the dtTrigger to rerender again
            this.dtTrigger.next();
        });
    }

    ngAfterViewInit(): void {this.dtTrigger.next();}


    buscaForm = this.fb.group({
        campo: "nome",
        tipo: "associado",
        valor: [''],

    });

    buscar(){
        this.rerender();

        this.apiService.busca(this.buscaForm.value).subscribe(response => {
            console.log(response);
            this.associaodos = response;


        });
    }


}