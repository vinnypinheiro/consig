import { RouterModule } from '@angular/router';

import {ConvenioGridComponent} from './convenio-grid/convenio-grid.component';
import {ConvenioFormComponent} from './convenio-form/convenio-form.component';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import { ConvenioViewComponent } from './convenio-view/convenio-view.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';


const ROUTES  = [
    {
        path: '',
        component: ConvenioGridComponent,
        data: {
            title: 'Cadastro de Convênio',
            urls: [
                { title: 'Dashboard', url: '/dashboard' },
                { title: 'Dashboard' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'new',
        component: ConvenioFormComponent,
        data: {
            title: 'Novo Convênio',
            urls: [
                { title: 'Convenios', url: '/convenio' },
                { title: 'Convênio' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: ConvenioViewComponent,
        canActivate: [AuthGuard]
    },
    {
        path: ':id',
        component: ConvenioFormComponent,
        canActivate: [AuthGuard]
    }];

@NgModule({
    imports: [RouterModule.forChild(ROUTES),
        SharedModule,
        FormsModule,
        ReactiveFormsModule
    ],
    declarations: [
        ConvenioGridComponent,
        ConvenioFormComponent, ConvenioViewComponent,
    ],
    entryComponents: [ConvenioGridComponent,ConvenioFormComponent],
    schemas: [],
    providers: []
})
export class ConvenioModule {}
