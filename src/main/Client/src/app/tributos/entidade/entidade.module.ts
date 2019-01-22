import { RouterModule } from '@angular/router'; 
 
import {EntidadeGridComponent} from './entidade-grid/entidade-grid.component'; 
import {EntidadeFormComponent} from './entidade-form/entidade-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                component: EntidadeGridComponent,
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                component: EntidadeFormComponent,
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [
                  RouterModule.forChild(ROUTES),
                  SharedModule
                ], 
                declarations: [ 
                  EntidadeGridComponent,
                  EntidadeFormComponent,
                ], 
                entryComponents: [
                  EntidadeGridComponent,
                  EntidadeFormComponent
                ],
                schemas: [], 
                providers: [] 
                }) 
        export class EntidadeModule {} 
