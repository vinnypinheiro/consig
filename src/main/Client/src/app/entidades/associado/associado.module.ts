import { RouterModule } from '@angular/router'; 
 
import {AssociadoGridComponent} from './associado-grid/associado-grid.component'; 
import {AssociadoFormComponent} from './associado-form/associado-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';

//Datatables
import { DataTablesModule } from 'angular-datatables';

//DatePicker
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule} from '@angular/material/';
import {MatFormFieldModule} from '@angular/material/form-field';



import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AssociadoViewComponent } from './associado-view/associado-view.component';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


const ROUTES  = [ 
        { 
            path: '', 
                    component: AssociadoGridComponent, 

        },
    {
        path: 'new',
        component: AssociadoFormComponent,
        data: {
            title: 'Novo Associado',
            urls: [
                { title: 'Associados', url: '/associado' },
                { title: 'Dashboard' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: AssociadoViewComponent,
        data: {
            title: 'Associado',
            urls: [
                {title: 'Associados', url: '/associado'},
                {title: 'Associado'}
            ]
        },

            canActivate: [AuthGuard]
    }];
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule ,
                    FormsModule,
                    ReactiveFormsModule,
                    NgbModule,
                    MatDatepickerModule,
                    MatNativeDateModule,
                    MatFormFieldModule,

                    DataTablesModule                ],
                declarations: [ 
                AssociadoGridComponent, 
                AssociadoFormComponent, AssociadoViewComponent, 
                ], 
                entryComponents: [AssociadoGridComponent,AssociadoFormComponent], 
                schemas: [], 
                providers: []
                }) 
        export class AssociadoModule {}
