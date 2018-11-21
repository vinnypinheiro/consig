import {Component, OnInit} from '@angular/core'; 
import {ActivatedRoute, Router} from '@angular/router'; 
import {CommonsForm} from '../../../commons-form'; 
import {FilterData} from '../../../components/interfaces'; 
import {CommonsService} from '../../../commons-service'; 
import {TipoOperador} from '../tipooperador'; 
import {TipoOperadorService} from '../tipooperador.service'; 

@Component({
 selector: 'gov-tipooperador-form', 
 templateUrl: './tipooperador-form.component.html', 
 //styleUrls: ['./tipooperador-form.component.css'] 
}) 
export class TipoOperadorFormComponent extends CommonsForm<TipoOperador> implements OnInit { 

    constructor(apiService: TipoOperadorService, 
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
             default: { 
                 console.log('filtro não disponibilizado para ', lookupValue.name) 
             } 
         } 
         return null; 
     } 

     onButtonActionClick(): void { 
         //console.log(this.activeBean.tipooperador.id); 
     } 
} 

