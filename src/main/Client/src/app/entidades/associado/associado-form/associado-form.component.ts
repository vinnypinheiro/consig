import {Component, OnInit} from '@angular/core';
import {Associado} from '../associado'; 
import {AssociadoService} from '../associado.service';

//react form
import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';
import {Operation} from "../../../utils/utils";

//comoons imports
import {ActivatedRoute, Router} from '@angular/router';
import {CommonsForm} from '../../../commons-form';
import {FilterData} from '../../../components/interfaces';
import {CommonsService} from '../../../commons-service';

import {Correspondente} from "../../correspondente/correspondente";

import { CorrespondenteService} from "../../correspondente/correspondente.service";


@Component({
 selector: 'gov-associado-form', 
 templateUrl: './associado-form.component.html', 
 //styleUrls: ['./associado-form.component.css'] 
}) 
export class AssociadoFormComponent extends CommonsForm<Associado> implements OnInit {

    correspondente: Correspondente [];

    constructor(private correspondenteService: CorrespondenteService,
    private fb: FormBuilder,
                apiService: AssociadoService,
                route: ActivatedRoute, 
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

                     console.log(this.activeBean)

                     this.activeForm.patchValue({
                         id: this.activeBean.id,

                         nome:  this.activeBean.nome,
                         cpf:   this.activeBean.cpf,
                         matricula:    this.activeBean.matricula,
                         situacao:   this.activeBean.situacao,
                         lotacao:    this.activeBean.lotacao,
                         cargo:    this.activeBean.cargo,
                         telefone:   this.activeBean.telefone,
                         email:  this.activeBean.email,
                         endereco:   this.activeBean.endereco,
                         estado:   this.activeBean.estado,
                         municipio:   this.activeBean.municipio,
                         datacadastro:   this.activeBean.datacadastro,
                         banco:   this.activeBean.banco,
                         agencia:    this.activeBean.agencia,
                         tipoconta:  this.activeBean.tipoconta,
                         conta:   this.activeBean.conta,
                         correspondente_id:  this.activeBean.correspondente_id

                     });



                 });
             }


         });

         this.correspondenteService.loadByFilter(this.getDefaultFilter()).subscribe(response => {
             this.correspondente = response.content;
         });

     } 

     getLookupService(lookupName: string): CommonsService<any> {
         switch (lookupName) { 
           
             default: { 
                 console.log('serviço não disponibilizado para ', lookupName) 
                 return this.apiService; 
             } 
         } 
     }


    //Associado reactive form
    activeForm = this.fb.group({

        nome:   null,
        cpf:   null,
        matricula:    null,
        cidade:  null,
        bairro:   null,
        uf:   null,
        cep:  null,
        situacao:    null,
        lotacao:     null,
        cargo:    null,
        telefone:   null,
        email:    null,
        endereco:   null,
        estado:    null,
        municipio:    null,
        datacadastro:   null,
        banco:    null,
        agencia:    null,
        tipoconta:   null,
        conta:   null,
        correspondente_id:  null,
        verbadesconto: null,
        vlrmensalidade: null,


    });



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

     onButtonActionClick(): void { 
         //console.log(this.activeBean.associado.id); 
     }

    saveAssociado() {
        // TODO: Use EventEmitter with form value
        console.warn(this.activeForm.value);
        this.activeBean = this.activeForm.value;
        this.save();
        //this.apiService.save(this.clienteForm.value);
    }
} 

