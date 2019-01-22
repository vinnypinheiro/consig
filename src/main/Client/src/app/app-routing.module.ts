import {LoginComponent} from "./security/login/login.component";
import {LogoutComponent} from "./security/logout/LogoutComponent";
import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {AuthGuard} from "./security/auth.guard";

import { FullComponent } from './layouts/full/full.component';

const routes: Routes = [
    {
        path: '',
        component: FullComponent,
        children: [
            { path: '', loadChildren: './starter/starter.module#StarterModule' },
            { path: 'associacao', loadChildren: './entidades/associacao/associacao.module#AssociacaoModule' },
            { path: 'convenio', loadChildren: './entidades/convenio/convenio.module#ConvenioModule' },
            { path: 'correspondente', loadChildren: './entidades/correspondente/correspondente.module#CorrespondenteModule' },
            { path: 'associado', loadChildren: './entidades/associado/associado.module#AssociadoModule' },
            {path: 'login', component: LoginComponent},
            {path: 'logout', component: LogoutComponent, canActivate: [AuthGuard]},
            {path: 'usuario', loadChildren: './entidades/usuario/usuario.module#UsuarioModule'},


            {path: 'agencia', loadChildren: './entidades/agencia/agencia.module#AgenciaModule'},

        ]}]

@NgModule({  exports: [RouterModule],  imports: [RouterModule.forRoot(routes)]})export class AppRoutingModule {}
 