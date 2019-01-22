import {Component, OnInit} from '@angular/core'; 
import {ActivatedRoute, Router} from '@angular/router'; 
import {CommonsForm} from '../../../commons-form'; 
import {FilterData} from '../../../components/interfaces'; 
import {CommonsService} from '../../../commons-service'; 
import {UnidadeGestora} from '../unidadegestora'; 
import {UnidadeGestoraService} from '../unidadegestora.service'; 

@Component({
 selector: 'gov-unidadegestora-form', 
 templateUrl: './unidadegestora-form.component.html', 
 //styleUrls: ['./unidadegestora-form.component.css'] 
}) 
export class UnidadeGestoraFormComponent extends CommonsForm<UnidadeGestora> implements OnInit { 

    constructor(apiService: UnidadeGestoraService, 
                route: ActivatedRoute, 
                router: Router ) {
         super(apiService, route, router); 
    }

     ngOnInit() { 
         super.ngOnInit(); 
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
             case 'tipologradouro': { 
                 return this.buildLookupFilter('trb_tipologradouro'); 
             }

             case 'cidade': { 
                 return this.buildLookupFilter('trb_cidade'); 
             }

             case 'bairro': {
               return this.buildLookupFilter('trb_bairro');
             }

             case 'logradouro': {
               return this.buildLookupFilter('trb_logradouro');
             }

             default: { 
                 console.log('filtro não disponibilizado para ', lookupValue.name) 
             } 
         } 
         return null; 
     } 

     onButtonActionClick(): void { 
         //console.log(this.activeBean.unidadegestora.id); 
     } 
} 

