import { RouterModule } from '@angular/router'; 
 
import {CorrespondenteGridComponent} from './correspondente-grid/correspondente-grid.component';
import {CorrespondenteFormComponent} from './correspondente-form/correspondente-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {SharedModule} from '../../shared/shared.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CorrespondenteViewComponent } from './correspondente-view/correspondente-view.component';

const ROUTES  = [ 
        { 
            path: '', 
                    component: CorrespondenteGridComponent, 
                    data: {
                        title: 'Cadastro de Correspondente',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                            { link: 'correspondente'}

                        ]
                      },
                canActivate: [AuthGuard] 
        },
    {
        path: 'view/:id',
        component: CorrespondenteViewComponent,
        data: {
            title: 'Correspondente',
            urls: [
                { title: 'Correspondentes', url: '/correspondente' },
                { title: 'Correspondente' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'new',
        component: CorrespondenteFormComponent,
        data: {
            title: 'Novo Correspondente',
            urls: [
                { title: 'Correspondentes', url: '/correspondente' },
                { title: 'Correspondente' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: CorrespondenteFormComponent,
        canActivate: [AuthGuard]
    }
];
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule ,
                    FormsModule,
                    ReactiveFormsModule,
                    NgbModule
                ], 
                declarations: [ 
                CorrespondenteGridComponent, 
                CorrespondenteFormComponent, CorrespondenteViewComponent, 
                ], 
                entryComponents: [CorrespondenteGridComponent,CorrespondenteFormComponent], 
                schemas: [], 
                providers: [] 
                }) 
        export class CorrespondenteModule {} 
