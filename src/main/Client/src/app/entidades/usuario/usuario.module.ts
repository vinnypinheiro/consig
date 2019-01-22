import { RouterModule } from '@angular/router'; 
 
import {UsuarioGridComponent} from './usuario-grid/usuario-grid.component'; 
import {UsuarioFormComponent} from './usuario-form/usuario-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                component: UsuarioGridComponent,
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                component: UsuarioFormComponent,
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [
                  RouterModule.forChild(ROUTES),
                  SharedModule
                ], 
                declarations: [ 
                  UsuarioGridComponent,
                  UsuarioFormComponent,
                ], 
                entryComponents: [
                  UsuarioGridComponent,
                  UsuarioFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class UsuarioModule {}
