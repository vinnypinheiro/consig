import {Component, OnInit, AfterViewInit, ViewEncapsulation, ViewChild} from '@angular/core';
import {Router, NavigationStart, NavigationEnd} from '@angular/router';
import {Helpers} from "./helpers";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Observable, Subject} from "rxjs";
import {debounceTime, switchMap} from "rxjs/operators";
import {IUserResponse} from "./utils/profiss.class";
import { SearchService } from "./search.service";
import {DataTableDirective} from "angular-datatables";
import { Associado } from './entidades/associado/associado';
import { AssociadoService } from './entidades/associado/associado.service';

@Component({
  selector: 'body',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  encapsulation: ViewEncapsulation.None,
})

export class AppComponent implements OnInit, AfterViewInit {

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<Associado> = new Subject();

  @ViewChild(DataTableDirective)
  dtElement: DataTableDirective;


  filteredUsers: Observable<IUserResponse>;
  usersForm: FormGroup;
  selectProfisData: Associado;

  profissionais: {};

  constructor(private fb: FormBuilder,
              private appService: SearchService ,
    private apiService: AssociadoService,
    private _router: Router) {}

  ngOnInit() {

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


    this.usersForm = this.fb.group({
      userInput: null
    })

    this.filteredUsers = this.usersForm.get('userInput').valueChanges
      .pipe(
        debounceTime(300),
        switchMap(value => this.appService.search({nome: value}, 1))
      );



    this._router.events.subscribe((route) => {
			if (route instanceof NavigationStart) {
				Helpers.setLoading(true);
				Helpers.bodyClass('fixed-navbar');
			}
			if (route instanceof NavigationEnd) {
				window.scrollTo(0, 0);
				Helpers.setLoading(false);

				// Initialize page: handlers ...
				Helpers.initPage();
			}

		});
  }

  buscaForm = this.fb.group({
    campo: "nome",
    valor: [''],
    tipo: 'associado',


  });

  closeShined() {
    $('body').removeClass('has-backdrop');
    $('.shined').removeClass('shined');
  }

  buscar(){
    this.rerender();

    this.apiService.busca(this.buscaForm.value).subscribe(response => {
      console.log(response);
      this.profissionais = response;


    });
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

}
