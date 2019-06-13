import { RouterModule } from '@angular/router'; 
 
import {RelatorioalbaGridComponent} from './relatorioalba-grid/relatorioalba-grid.component';
import {AuthGuard} from '../../security/auth.guard';
import {NgModule} from '@angular/core'; 
import {SharedModule} from '../../shared/shared.module';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RelatorioAlbaViewComponent } from './relatorioalba-view/relatorioalba-view.component';

const ROUTES  = [ 
        { 
            path: '', 
                    component: RelatorioalbaGridComponent,
                    data: {
                        title: 'Cadastro de RelatorioAlba',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                            { link: 'relatorioAlba'}

                        ]
                      },
                canActivate: [AuthGuard] 
        },
    {
        path: 'view/:id',
        component: RelatorioAlbaViewComponent,
        data: {
            title: 'RelatorioAlba',
            urls: [
                { title: 'RelatorioAlbas', url: '/relatorioAlba' },
                { title: 'RelatorioAlba' }
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
                RelatorioalbaGridComponent,
                 RelatorioAlbaViewComponent,
                ], 
                entryComponents: [RelatorioalbaGridComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class RelatorioalbaModule {}
