import { RouterModule } from '@angular/router'; 
 
import {ContaReceberGridComponent} from './contareceber-grid/contareceber-grid.component';
import {ContaReceberFormComponent} from './contareceber-form/contareceber-form.component';
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {ContaReceberService} from './contareceber.service';
import {SharedModule} from '../../shared/shared.module';
import { ContaReceberViewComponent } from './contareceber-view/contareceber-view.component';
import { NgbDate, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

//Datatables
import { DataTablesModule } from 'angular-datatables';

 
const ROUTES  = [ 
        { 
            path: '', 
                    component: ContaReceberViewComponent,
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
        component: ContaReceberViewComponent,
        canActivate: [AuthGuard]
    },
        { 
            path: ':id', 
                    component: ContaReceberFormComponent,
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
                ContaReceberGridComponent,
                ContaReceberFormComponent, ContaReceberViewComponent,
                ], 
                entryComponents: [ContaReceberGridComponent,ContaReceberFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class ContaReceberModule {}
