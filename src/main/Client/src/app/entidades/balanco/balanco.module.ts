import { RouterModule } from '@angular/router'; 
 
import {BalancoGridComponent} from './balanco-grid/balanco-grid.component';
import {BalancoFormComponent} from './balanco-form/balanco-form.component';
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {BalancoService} from './balanco.service';
import {SharedModule} from '../../shared/shared.module';
import { BalancoViewComponent } from './balanco-view/balanco-view.component';
import { NgbDate, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//Datatables
import { DataTablesModule } from 'angular-datatables';

 
const ROUTES  = [ 
        { 
            path: '', 
                    component: BalancoViewComponent,
                    data: {
                        title: 'Balanço financeiro',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                          { title: 'Dashboard' }
                        ]
                      },
                canActivate: [AuthGuard] 
        }, {
        path: 'view/:id',
        component: BalancoViewComponent,
        canActivate: [AuthGuard]
    },
        { 
            path: ':id', 
                    component: BalancoFormComponent,
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule,
                    NgbModule,
                    DataTablesModule,
                    FormsModule,
                    ReactiveFormsModule
                ], 
                declarations: [ 
                BalancoGridComponent,
                BalancoFormComponent, BalancoViewComponent,
                ], 
                entryComponents: [BalancoGridComponent,BalancoFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class BalancoModule {}
