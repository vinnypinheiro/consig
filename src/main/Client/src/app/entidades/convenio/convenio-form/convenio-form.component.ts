import {Component, OnInit} from '@angular/core'; 
import {ActivatedRoute, Router} from '@angular/router'; 
import {CommonsForm} from '../../../commons-form'; 
import {FilterData} from '../../../components/interfaces'; 
import {CommonsService} from '../../../commons-service'; 
import {Convenio} from '../convenio';
import {ConvenioService} from '../convenio.service';

import {AssociacaoService} from "../../associacao/associacao.service";

//react form
import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';
import {Operation} from "../../../utils/utils";
import {Associacao} from "../../associacao/associacao";


@Component({
 selector: 'gov-associacao-form', 
 templateUrl: './convenio-form.component.html',
 //styleUrls: ['./associacao-form.component.css'] 
}) 
export class ConvenioFormComponent extends CommonsForm<Convenio> implements OnInit {

    associacao: Associacao [];

    constructor(
       private associacaoService: AssociacaoService,
        apiService: ConvenioService,
                route: ActivatedRoute,
                private fb: FormBuilder,
 router: Router ) { 
         super(apiService, route, router); 
     } 

     ngOnInit() {
         this.beanSubscribe = this.route.params.subscribe(params => {
             this.beanId = params['id'];
             this.beanId = "novo";
             if(this.beanId === "novo"){
                 this.beanId = "";
                 this.operation = Operation.CREATE;
             }else {
                 this.operation = Operation.SELECT;
                 this.apiService.findById(this.beanId).subscribe(response => {
                     this.activeBean = (<any>response);

                     console.log(this.activeBean);
                 });
             }


         });

         this.associacaoService.loadByFilter(this.getDefaultFilter()).subscribe(response => {
             this.associacao = response.content;
         });

     }


    //Cliente reactive form
    activeForm = this.fb.group({
        razaosocial:  null,
        nomefantasia: null,
        inscricaoestadual:  null,
        inscricaomunicipal: null,
        cnpj:  null,
        datainicial:   null,
        datafinal:  null,

    });

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
             default: { 
                 console.log('filtro não disponibilizado para ', lookupValue.name) 
             } 
         } 
         return null; 
     } 

     onButtonActionClick(): void { 
         //console.log(this.activeBean.associacao.id); 
     } 
} 

