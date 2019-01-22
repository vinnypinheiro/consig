import { RouterModule } from '@angular/router'; 
 
import {AgenciaGridComponent} from './agencia-grid/agencia-grid.component'; 
import {AgenciaFormComponent} from './agencia-form/agencia-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                    component: AgenciaGridComponent, 
                    data: {
                        title: 'Cadastro de Associados',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                          { title: 'Dashboard' }
                        ]
                      },
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                    component: AgenciaFormComponent, 
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule 
                ], 
                declarations: [ 
                AgenciaGridComponent, 
                AgenciaFormComponent, 
                ], 
                entryComponents: [AgenciaGridComponent,AgenciaFormComponent], 
                schemas: [], 
                providers: [] 
                }) 
        export class AgenciaModule {} 
