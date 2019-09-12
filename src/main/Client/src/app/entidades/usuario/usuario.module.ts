import {RouterModule, Routes} from '@angular/router'; 
 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {UsuarioComponent} from "./usuario.component";
import {UsuarioGridComponent} from "./usuario-grid/usuario-grid.component";
import {AuthGuard} from "../../security/auth.guard";
import { ReactiveFormsModule } from '@angular/forms';
import {UsuarioFormComponent} from "./usuario-form/usuario-form.component";
import {UsuarioFormNovoComponent} from "./usuario-form-novo/usuario-form-novo.component";

const routes: Routes = [
  { path: '', component: UsuarioGridComponent, canActivate: [AuthGuard] },

  { path: ':id',  component: UsuarioFormComponent , canActivate: [AuthGuard] },
  { path: 'novo',  component: UsuarioFormNovoComponent , canActivate: [AuthGuard] },



];

 
        @NgModule({ 
                imports: [RouterModule.forChild(routes),
                SharedModule ,
                  ReactiveFormsModule
                ], 
                declarations: [ 
                  UsuarioComponent,
                  UsuarioGridComponent,
                  UsuarioFormComponent,
                  UsuarioFormNovoComponent
                ], 
                entryComponents: [UsuarioComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class UsuarioModule {} 
