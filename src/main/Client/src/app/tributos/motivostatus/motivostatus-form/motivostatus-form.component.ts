import {Component, OnInit} from '@angular/core'; 
import {ActivatedRoute, Router} from '@angular/router'; 
import {CommonsForm} from '../../../commons-form'; 
import {FilterData} from '../../../components/interfaces'; 
import {CommonsService} from '../../../commons-service'; 
import {MotivoStatus} from '../motivostatus'; 
import {MotivoStatusService} from '../motivostatus.service'; 

@Component({
 selector: 'gov-motivostatus-form', 
 templateUrl: './motivostatus-form.component.html', 
 //styleUrls: ['./motivostatus-form.component.css'] 
}) 
export class MotivoStatusFormComponent extends CommonsForm<MotivoStatus> implements OnInit { 

    constructor(apiService: MotivoStatusService, 
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
         //console.log(this.activeBean.motivostatus.id); 
     } 
} 

