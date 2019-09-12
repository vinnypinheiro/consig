import { RouterModule } from '@angular/router'; 
 
import {AssociacaoGridComponent} from './associacao-grid/associacao-grid.component'; 
import {AssociacaoFormComponent} from './associacao-form/associacao-form.component'; 
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {AssociacaoService} from './associacao.service'; 
import {SharedModule} from '../../shared/shared.module';
import { AssociacaoViewComponent } from './associacao-view/associacao-view.component';

 
const ROUTES  = [ 
        { 
            path: '', 
                    component: AssociacaoGridComponent, 
                    data: {
                        title: 'Cadastro de Associação',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                          { title: 'Dashboard' }
                        ]
                      },
                canActivate: [AuthGuard] 
        }, {
        path: 'view/:id',
        component: AssociacaoViewComponent,
        canActivate: [AuthGuard]
    },
        { 
            path: ':id', 
                    component: AssociacaoFormComponent, 
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule
                    
                ], 
                declarations: [ 
                AssociacaoGridComponent, 
                AssociacaoFormComponent, AssociacaoViewComponent, 
                ], 
                entryComponents: [AssociacaoGridComponent,AssociacaoFormComponent], 
                schemas: [], 
                providers: [] 
                }) 
        export class AssociacaoModule {} 
