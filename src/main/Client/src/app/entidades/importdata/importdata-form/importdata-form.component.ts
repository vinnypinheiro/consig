import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Importdata} from '../importdata'; 
import {ImportdataService} from '../importdata.service';

import { FileUploader } from 'ng2-file-upload/ng2-file-upload';
const URL = 'http://192.168.0.4:8080/importdata/importcsv';

//react form
import { FormBuilder } from '@angular/forms';
import {Operation} from "../../../utils/utils";

//comoons imports
import {ActivatedRoute, Router} from '@angular/router';
import {CommonsForm} from '../../../commons-form';
import {FilterData} from '../../../components/interfaces';
import {CommonsService} from '../../../commons-service';

@Component({
 selector: 'gov-importdata-form', 
 templateUrl: './importdata-form.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
    styleUrls: ['./importdata-form.component.css']
}) 
export class ImportdataFormComponent extends CommonsForm<Importdata> implements OnInit {

    public operation: Operation;

    constructor(private fb: FormBuilder,
                apiService: ImportdataService,
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
         //console.log(this.activeBean.importdata.id); 
     }

    saveImportdata() {
        // TODO: Use EventEmitter with form value
        //this.save();
        this.apiService.importcsv().subscribe(respose => {
            console.log("teste ok", respose);
        });
    }
} 

