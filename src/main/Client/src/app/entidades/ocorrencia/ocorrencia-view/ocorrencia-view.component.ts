import {Component, OnInit, AfterViewInit, Input} from '@angular/core';
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


@Component({
 selector: 'gov-ocorrencia-view',
 templateUrl: './Ocorrencia-view.component.html',
 //styleUrls: ['./Ocorrencia-form.component.css'] 
}) 
export class OcorrenciaViewComponent extends CommonsForm<Ocorrencia> implements OnInit {

     @Input() ocorrenciaView;

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

  console.log(this.ocorrenciaView);
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

