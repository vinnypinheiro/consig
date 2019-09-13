import {Component, OnInit, AfterViewInit, Input, EventEmitter} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router'; 
import {CommonsForm} from '../../../commons-form'; 
import {FilterData} from '../../../components/interfaces'; 
import {CommonsService} from '../../../commons-service'; 
import {Ocorrencia} from '../Ocorrencia';
import {OcorrenciaService} from '../ocorrencia.service';

import { ToastrService } from 'ngx-toastr';


import { ScriptLoaderService } from '../../../_services/script-loader.service';
declare var $:any;

import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {OcorrenciaGridComponent} from "../ocorrencia-grid/ocorrencia-grid.component";


@Component({
 selector: 'gov-asasasas-form',
 templateUrl: './Ocorrencia-form.component.html', 
 //styleUrls: ['./Ocorrencia-form.component.css'] 
}) 
export class OcorrenciaFormComponent extends CommonsForm<Ocorrencia> implements OnInit, AfterViewInit {

    usuario: any;
    activeUser: any;
    @Input() activeEntity;

    constructor(apiService: OcorrenciaService,
                route: ActivatedRoute,
                private fb: FormBuilder,
                public activeModal: NgbActiveModal,
                private _script: ScriptLoaderService,
                private toastr: ToastrService,
 router: Router ) { 
         super(apiService, route, router); 
     } 

     ngOnInit() { 
        this.beanSubscribe = this.route.params.subscribe(params => {
            this.beanId = params['id'];
            this.getOcorrencias( this.beanId);

        });

        this.usuario = JSON.parse(localStorage.getItem( "loginResponse"));
        console.log(this.usuario);
        this.activeUser = this.usuario.usuario;
       console.log(this.activeUser.id);
     }


  getOcorrencias(intityId){
    this.apiService.findById(intityId).subscribe(response => {
      this.activeBean = (<any>response);

      this.ocorrenciaForm.patchValue({
        id: this.activeBean.id,
        data: this.activeBean.data,
        descricao: this.activeBean.descricao,


      });
    });

  }

     // Ocorrencia Form
      ocorrenciaForm = this.fb.group({
        id: null,
      login: null,
      descricao: null,
        associado_id: null,

  });

  saveOcorrencia(){
    this.ocorrenciaForm.value;
    this.ocorrenciaForm.patchValue({
      usuario_id: this.activeUser.id,
      associado_id: this.activeEntity.id,
      login: this.activeUser.nome

    });

    this.apiService.save(this.ocorrenciaForm.value).subscribe(response => {
      console.log(response);
      this.activeModal.close();
      this.getOcorrencias( this.activeEntity.id);

    });

    this.toastr.success('',  'Ocorrência registrada com sucesso '  );

  }

     ngAfterViewInit() { 
        this._script.load('./assets/js/scripts/form-plugins.js');
       this._script.load('./assets/js/scripts/toastr-demo.js');


      }

     getLookupService(lookupName: string): CommonsService<any> {
         switch (lookupName) {

             default: { 
                 console.log('serviço não disponibilizado para ', lookupName) 
                 return this.apiService; 
             } 
         } 
     } 

     getDeLookupFilter(lookupValue: any): FilterData {
         switch (lookupValue.name) { 
             case 'uf': { 
                 return this.buildLookupFilter('uf'); 
             }

             default: { 
                 console.log('filtro não disponibilizado para ', lookupValue.name) 
             } 
         } 
         return null; 
     } 

     getDefaultFilter(): FilterData { 
        const filterData = super.getDefaultFilter(); 
        return filterData; 
    } 

} 

