import {Component, Input ,OnInit} from '@angular/core';
import {Router} from '@angular/router'; 
import {FilterData} from '../../../components/interfaces'; 
import {FieldSearch} from '../../../utils/utils'; 
 import {CommonsGrid} from '../../../commons-grid'; 
import {ReportGroup} from '../../../shared/report-group'; 
import {Ocorrencia} from '../Ocorrencia';
import {OcorrenciaService} from '../ocorrencia.service';
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {OcorrenciaFormComponent} from "../ocorrencia-form/ocorrencia-form.component";
import { Subject } from 'rxjs';
import {OcorrenciaViewComponent} from "../ocorrencia-view/ocorrencia-view.component";

declare var $:any;


@Component({ 
  selector: 'app-ocorrencia-grid', 
  templateUrl: './Ocorrencia-grid.component.html', 
  //styleUrls: ['./Ocorrencia-grid.component.css'] 
}) 
export class OcorrenciaGridComponent extends CommonsGrid<Ocorrencia> implements OnInit {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<Ocorrencia> = new Subject();

  ocorrencias: Ocorrencia;
  @Input() activeEntity;

     constructor(private modalService:NgbModal,
                 public activeModal: NgbActiveModal,
                 apiService: OcorrenciaService,
                 router: Router) {
         super(apiService, router); 
     } 
     ngOnInit() {

         this.loadByCpad(this.activeEntity.id);

       this.dtOptions = {
         pagingType: 'full_numbers',
         retrieve: true,
         pageLength: 5,
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

     loadByCpad(id) {
       this.apiService.findOcorrenciaById(id).subscribe(response => {
         this.ocorrencias = <any>response;
         this.dtTrigger.next();
         console.log(this.ocorrencias);

       });
        
    }

     getDefaultFilter(): FilterData { 
         const filterData = super.getDefaultFilter(); 
         filterData.order = 'Ocorrencia.bairro, Ocorrencia.banco, Ocorrencia.bancoOcorrencia, Ocorrencia.bancoconta, Ocorrencia.cidade, Ocorrencia.cnpj, Ocorrencia.contato, Ocorrencia.cpf, Ocorrencia.datacadastro, Ocorrencia.email, Ocorrencia.email1, Ocorrencia.email2, Ocorrencia.emailcontato, Ocorrencia.endereco, Ocorrencia.fax, Ocorrencia.inscricaoestadual, Ocorrencia.inscricaomunicipal, Ocorrencia.nomefantasia, Ocorrencia.obs, Ocorrencia.razaosocial, Ocorrencia.site, Ocorrencia.telefone, Ocorrencia.telefone2 ' 
         return filterData; 
     } 

     getRowFilter(): FilterData { 
         let filter= this.buildRowFilter('trb_Ocorrencia'); 
         filter.whereClauses[0].dataType='INTEGER'; 
         return filter; 
     } 

     getTableName(): string { 
         return 'Ocorrencia' ; 
     } 

     getFieldList(): FieldSearch[] { 
         let retorno = []; 
         retorno[0] = new FieldSearch('bairro','bairro','STRING'); 
         retorno[1] = new FieldSearch('banco','banco','STRING'); 
         retorno[2] = new FieldSearch('bancoOcorrencia','bancoOcorrencia','STRING'); 
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

     getReportList():ReportGroup[]{return [];}

  ngAfterViewInit() {

  }

  openNovaOcorrenciaModal() {

    const modalNovaOcorrencia =  this.modalService.open( OcorrenciaFormComponent, {
      size: 'lg'
    });

    modalNovaOcorrencia.componentInstance.activeEntity = this.activeEntity ;
    modalNovaOcorrencia.result.then((result) => {
      this.dtOptions.destroy;
      this.ngOnInit();
    } );

  }

  openViewOcorrenciaModal(ocorrenciaView) {

    const modalViewOcorrencia =  this.modalService.open(OcorrenciaViewComponent, {
      size: 'lg'
    });

    modalViewOcorrencia.componentInstance.ocorrenciaView = ocorrenciaView ;

  }


  removeOcorrencia(id){
    this.apiService.delete(id).subscribe(response => {
      console.log(response);
      this.ngOnInit();
    });

  }

}
