import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Espelhoretorno} from '../espelhoretorno';
import {EspelhoRetornoService} from '../espelhoretorno.service';

import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
const URL = 'http://10.1.1.239:8080/espelhoretorno/importcsv';

//react form
import { FormBuilder } from '@angular/forms';
import {Operation} from "../../../utils/utils";

//comoons imports
import {ActivatedRoute, Router} from '@angular/router';
import {CommonsForm} from '../../../commons-form';
import {FilterData} from '../../../components/interfaces';
import {CommonsService} from '../../../commons-service';

@Component({
 selector: 'gov-espelhoretorno-form', 
 templateUrl: './espelhoretorno-form.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
    styleUrls: ['./espelhoretorno-form.component.css']
}) 
export class EspelhoretornoFormComponent extends CommonsForm<Espelhoretorno> implements OnInit {

    public operation: Operation;

    constructor(private fb: FormBuilder,
                apiService: EspelhoRetornoService,
                route: ActivatedRoute, 
 router: Router ) { 
         super(apiService, route, router); 
     } 

     ngOnInit() {

     }

    uploader: FileUploader = new FileUploader({
        url: URL,
        isHTML5: true
    });
    hasBaseDropZoneOver = false;
    hasAnotherDropZoneOver = false;

    // Angular2 File Upload
    fileOverBase(e: any): void {
        this.hasBaseDropZoneOver = e;
    }

    fileOverAnother(e: any): void {
        this.hasAnotherDropZoneOver = e;
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
         //console.log(this.activeBean.espelhoretorno.id); 
     }

    importEspelhoRetorno() {
        // TODO: Use EventEmitter with form value
        //this.save();
        this.apiService.espelhoretorno().subscribe(respose => {
            console.log("teste ok", respose);
        });
    }

    importRetornoAlba() {
        // TODO: Use EventEmitter with form value
        //this.save();
        this.apiService.importretornoalba().subscribe(respose => {
            console.log("teste ok", respose);
        });
    }
} 

