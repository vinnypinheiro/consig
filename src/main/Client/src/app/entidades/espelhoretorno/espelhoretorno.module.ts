import { RouterModule } from '@angular/router'; 
 
import {EspelhoretornoFormComponent} from './espelhoretorno-form/espelhoretorno-form.component';
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {SharedModule} from '../../shared/shared.module';

import { FileUploadModule } from 'ng2-file-upload/ng2-file-upload';


import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const ROUTES  = [ 
        { 
            path: '', 
                    component: EspelhoretornoFormComponent,
                    data: {
                        title: 'Importação de Retorno CONSIGLOG',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                            { link: 'espelhoretorno'}

                        ]
                      },
                canActivate: [AuthGuard] 
        },

    {
        path: 'new',
        component: EspelhoretornoFormComponent,
        data: {
            title: 'Novo EspelhoRetorno',
            urls: [
                { title: 'EspelhoRetornos', url: '/espelhoretorno' },
                { title: 'EspelhoRetorno' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: EspelhoretornoFormComponent,
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
                EspelhoretornoFormComponent,
                ], 
                entryComponents: [EspelhoretornoFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class EspelhoretornoModule {} 
