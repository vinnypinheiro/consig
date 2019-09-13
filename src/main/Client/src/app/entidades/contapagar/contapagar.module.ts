import { RouterModule } from '@angular/router'; 
 
import {ContaPagarGridComponent} from './contaPagar-grid/contaPagar-grid.component';
import {ContaPagarFormComponent} from './contaPagar-form/contaPagar-form.component';
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {ContaPagarService} from './contaPagar.service';
import {SharedModule} from '../../shared/shared.module';
import { ContaPagarViewComponent } from './contaPagar-view/contaPagar-view.component';
import { NgbDate, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//Datatables
import { DataTablesModule } from 'angular-datatables';

 
const ROUTES  = [ 
        {
            path: '', 
                    component: ContaPagarViewComponent,
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
        component: ContaPagarViewComponent,
        canActivate: [AuthGuard]
    },
        { 
            path: ':id', 
                    component: ContaPagarFormComponent,
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
                ContaPagarGridComponent,
                ContaPagarFormComponent, ContaPagarViewComponent,
                ], 
                entryComponents: [ContaPagarGridComponent,ContaPagarFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class ContaPagarModule {}
