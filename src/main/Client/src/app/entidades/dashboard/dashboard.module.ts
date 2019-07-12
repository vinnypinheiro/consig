import { RouterModule } from '@angular/router'; 
 
import {DashboardGridComponent} from './dashboard-grid/dashboard-grid.component';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core'; 
import {SharedModule} from '../../shared/shared.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const ROUTES  = [ 
        { 
            path: '', 
                    component: DashboardGridComponent,
                    data: {
                        title: 'Cadastro de Dashboard',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                            { link: 'dashboard'}

                        ]
                      },
                canActivate: [AuthGuard] 
        }
];
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule ,
                    FormsModule,
                    ReactiveFormsModule,
                    NgbModule
                ], 
                declarations: [ 
                DashboardGridComponent
                ], 
                entryComponents: [DashboardGridComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class DashboardModule {}
