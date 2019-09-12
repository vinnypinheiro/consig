import {Component, OnInit, ViewChild} from '@angular/core';
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
export class AssociadoGridComponent extends CommonsGrid<Associado> implements OnInit {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<Associado> = new Subject();

  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;


  associados: {};

  constructor(apiService: AssociadoService,
              private fb: FormBuilder,
              router: Router) {
    super(apiService, router);
  }

  ngOnInit() {

    // this.loadByFilter(this.getDefaultFilter());

    this.dtOptions = {
      pagingType: 'full_numbers',
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

  ngAfterViewInit(): void {this.dtTrigger.next();}

  buscaForm = this.fb.group({
    campo: "nome",
    valor: [''],
    tipo: "associado"

  });

  loadByFilter(filterData: FilterData) {
    this.apiService.loadByFilter(filterData).subscribe(response => {
      this.associados = response;
      this.dtTrigger.next();

    });

  }

  onNavigateClick(filterData: FilterData): void {
    this.loadByFilter(filterData);
  }

  getDefaultFilter(): FilterData {
    const filterData = super.getDefaultFilter();
    filterData.order = 'Profissional.bairro, Profissional.banco, Profissional.bancoProfissional, Profissional.bancoconta, Profissional.cidade, Profissional.cnpj, Profissional.contato, Profissional.cpf, Profissional.datacadastro, Profissional.email, Profissional.email1, Profissional.email2, Profissional.emailcontato, Profissional.endereco, Profissional.fax, Profissional.inscricaoestadual, Profissional.inscricaomunicipal, Profissional.nomefantasia, Profissional.obs, Profissional.razaosocial, Profissional.site, Profissional.telefone, Profissional.telefone2 '
    return filterData;
  }

  rerender(): void {
    this.dtElement.dtInstance.then((dtInstance: DataTables.Api) => {
      // Destroy the table first
      dtInstance.destroy();
      // Call the dtTrigger to rerender again
      this.dtTrigger.next();
    });
  }

  buscar(){
    this.rerender();

    this.apiService.busca(this.buscaForm.value).subscribe(response => {
      console.log(response);
      this.associados = response;


    });
  }

  importData(){
    this.apiService.importcsv().subscribe(respose => {
      console.log("teste ok", respose);
    });
  }

  getRowFilter(): FilterData {
    let filter= this.buildRowFilter('trb_Profissional');
    filter.whereClauses[0].dataType='INTEGER';
    return filter;
  }

  getTableName(): string {
    return 'Profissional' ;
  }

  getFieldList(): FieldSearch[] {
    let retorno = [];
    retorno[0] = new FieldSearch('bairro','bairro','STRING');

    return retorno;
  }
  getReportList():ReportGroup[]{
    return [];
  }
}
