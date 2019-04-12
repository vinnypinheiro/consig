import {Component, OnInit} from '@angular/core';
import {Correspondente} from '../correspondente'; 
import {CorrespondenteService} from '../correspondente.service';

//react form
import { FormBuilder, FormGroup, FormArray, Validators, FormControl } from '@angular/forms';
import {Operation} from "../../../utils/utils";

//comoons imports
import {ActivatedRoute, Router} from '@angular/router';
import {CommonsForm} from '../../../commons-form';
import {FilterData} from '../../../components/interfaces';
import {CommonsService} from '../../../commons-service';

@Component({
 selector: 'gov-correspondente-form', 
 templateUrl: './correspondente-form.component.html', 
 //styleUrls: ['./correspondente-form.component.css'] 
}) 
export class CorrespondenteFormComponent extends CommonsForm<Correspondente> implements OnInit {

    public operation: Operation;

    constructor(private fb: FormBuilder,
                apiService: CorrespondenteService,
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

                         razaosocial: this.activeBean.razaosocial,
                     nomefantasia: this.activeBean.nomefantasia,
                     inscricaoestadual: this.activeBean.inscricaoestadual,
                     inscricaomunicipal: this.activeBean.inscricaomunicipal,
                     cnpj: this.activeBean.cnpj,
                     endereco: this.activeBean.endereco,
                     cidade: this.activeBean.cidade,
                     uf: this.activeBean.uf,
                     cep: this.activeBean.cep,
                     bairro: this.activeBean.bairro,
                     telefone: this.activeBean.telefone,
                     email: this.activeBean.email,
                     site: this.activeBean.site,

                     });



                 });
             }


         });
     }


    //Cliente reactive form
    activeForm = this.fb.group({
        razaosocial: [''],
        nomefantasia:  [''],
        inscricaoestadual: [''],
        inscricaomunicipal:  [''],
        cnpj:  [''],
        endereco: [''],
        cidade: [''],
        uf: [''],
        cep: [''],
        bairro: [''],
        telefone: [''],
        email: [''],
        site: [''],


        associadoList: this.fb.array([this.addAssociadoGroup()]),

    });

    addAssociadoGroup() {
        return this.fb.group({
            nome: [null],
            cpf:[null],
            valoremprestimo:[null],
            mensalidade:[null],
            prestacao: [null],
            qtdprestacoes: [null],
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
         //console.log(this.activeBean.correspondente.id); 
     }

    saveCorrespondente() {
        // TODO: Use EventEmitter with form value
        console.warn(this.activeForm.value);
        this.activeBean = this.activeForm.value;
        this.save();
        //this.apiService.save(this.clienteForm.value);
    }
} 

