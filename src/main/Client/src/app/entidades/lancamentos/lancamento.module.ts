import { RouterModule } from '@angular/router'; 
 
import {LancamentoGridComponent} from './lancamento-grid/lancamento-grid.component'; 
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

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';


const ROUTES  = [ 
        { 
            path: '', 
                    component: LancamentoGridComponent, 

        },
    {
        path: 'new',
        component: LancamentoGridComponent,
        data: {
            title: 'Novo Lancamento',
            urls: [
                { title: 'Lancamentos', url: '/lancamento' },
                { title: 'Dashboard' }
            ]
        },
        canActivate: [AuthGuard]
    },
    {
        path: 'view/:id',
        component: LancamentoGridComponent,
        data: {
            title: 'Lancamento',
            urls: [
                {title: 'Lancamentos', url: '/lancamento'},
                {title: 'Lancamento'}
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
                LancamentoGridComponent,

                ], 
                entryComponents: [LancamentoGridComponent],
                schemas: [], 
                providers: []
                }) 
        export class LancamentoModule {}
