import { RouterModule } from '@angular/router'; 
 
import {UnidadeGestoraGridComponent} from './unidadegestora-grid/unidadegestora-grid.component'; 
import {UnidadeGestoraFormComponent} from './unidadegestora-form/unidadegestora-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                component: UnidadeGestoraGridComponent,
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                component: UnidadeGestoraFormComponent,
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [
                  RouterModule.forChild(ROUTES),
                  SharedModule
                ], 
                declarations: [ 
                  UnidadeGestoraGridComponent,
                  UnidadeGestoraFormComponent,
                ], 
                entryComponents: [
                  UnidadeGestoraGridComponent,
                  UnidadeGestoraFormComponent
                ],
                schemas: [], 
                providers: [] 
                }) 
        export class UnidadeGestoraModule {} 
