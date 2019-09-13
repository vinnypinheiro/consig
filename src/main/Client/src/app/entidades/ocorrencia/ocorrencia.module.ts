import { RouterModule,Routes } from '@angular/router';
 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {OcorrenciaGridComponent} from './ocorrencia-grid/ocorrencia-grid.component';
import {OcorrenciaFormComponent} from './ocorrencia-form/ocorrencia-form.component';
import { ReactiveFormsModule } from '@angular/forms';

import {OcorrenciaComponent} from "./ocorrencia.component";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { DataTablesModule } from 'angular-datatables';
import {OcorrenciaViewComponent} from "./ocorrencia-view/ocorrencia-view.component";


const routes: Routes = [


];

 
        @NgModule({ 
                imports: [RouterModule.forChild(routes),
                SharedModule ,
                  ReactiveFormsModule,
                    DataTablesModule,
                  NgbModule
                ], 
                declarations: [ 
                        OcorrenciaGridComponent,OcorrenciaFormComponent, OcorrenciaComponent,OcorrenciaViewComponent
                ], 
                entryComponents: [OcorrenciaFormComponent,OcorrenciaComponent,OcorrenciaGridComponent,OcorrenciaViewComponent],
                schemas: [], 
                providers: [] ,
          exports:[
            OcorrenciaGridComponent
          ]
                }) 
        export class OcorrenciaModule {}


