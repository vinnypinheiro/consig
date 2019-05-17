import { RouterModule } from '@angular/router'; 
 
import {CorretorGridComponent} from './corretor-grid/corretor-grid.component';
import {CorretorFormComponent} from './corretor-form/corretor-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {SharedModule} from '../../shared/shared.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CorretorViewComponent } from './corretor-view/corretor-view.component';

const ROUTES  = [ 
        { 
            path: '', 
                    component: CorretorGridComponent, 
                    data: {
                        title: 'Cadastro de Corretor',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                            { link: 'corretor'}

                        ]
                      },
                canActivate: [AuthGuard] 
        },
    {
        path: 'view/:id',
        component: CorretorViewComponent,
        data: {
            title: 'Corretor',
            urls: [
                { title: 'Corretors', url: '/corretor' },
                { title: 'Corretor' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'new',
        component: CorretorFormComponent,
        data: {
            title: 'Novo Corretor',
            urls: [
                { title: 'Corretors', url: '/corretor' },
                { title: 'Corretor' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: CorretorFormComponent,
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
                CorretorGridComponent, 
                CorretorFormComponent, CorretorViewComponent, 
                ], 
                entryComponents: [CorretorGridComponent,CorretorFormComponent], 
                schemas: [], 
                providers: [] 
                }) 
        export class CorretorModule {} 
