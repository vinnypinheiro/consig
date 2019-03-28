import { RouterModule } from '@angular/router'; 
 
import {ImportdataGridComponent} from './importdata-grid/importdata-grid.component';
import {ImportdataFormComponent} from './importdata-form/importdata-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {SharedModule} from '../../shared/shared.module';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';


import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ImportdataViewComponent } from './importdata-view/importdata-view.component';

const ROUTES  = [ 
        { 
            path: '', 
                    component: ImportdataFormComponent,
                    data: {
                        title: 'Importação de arquivo Excel',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                            { link: 'importdata'}

                        ]
                      },
                canActivate: [AuthGuard] 
        },
    {
        path: 'view/:id',
        component: ImportdataViewComponent,
        data: {
            title: 'Importdata',
            urls: [
                { title: 'Importdatas', url: '/importdata' },
                { title: 'Importdata' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'new',
        component: ImportdataFormComponent,
        data: {
            title: 'Novo Importdata',
            urls: [
                { title: 'Importdatas', url: '/importdata' },
                { title: 'Importdata' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: ImportdataFormComponent,
        canActivate: [AuthGuard]
    }
];
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule ,
                    FormsModule,
                    ReactiveFormsModule,
                    NgbModule,
                    FileUploadModule
                ], 
                declarations: [ 
                ImportdataGridComponent, 
                ImportdataFormComponent, ImportdataViewComponent, 
                ], 
                entryComponents: [ImportdataGridComponent,ImportdataFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class ImportdataModule {} 
