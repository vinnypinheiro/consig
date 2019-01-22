import { RouterModule } from '@angular/router'; 
 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {OperadorService} from './operador.service'; 
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                   
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule 
                ], 
                declarations: [ 
                ], 
                entryComponents: [], 
                schemas: [], 
                providers: [] 
                }) 
        export class OperadorModule {} 
