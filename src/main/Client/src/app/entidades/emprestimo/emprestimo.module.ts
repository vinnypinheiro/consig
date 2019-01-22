import { RouterModule } from '@angular/router'; 
 
import {EmprestimoGridComponent} from './emprestimo-grid/emprestimo-grid.component';
import {EmprestimoFormComponent} from './emprestimo-form/emprestimo-form.component';
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {EmprestimoService} from './emprestimo.service';
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                    component: EmprestimoGridComponent,
                    data: {
                        title: 'Cadastro de Emprestimo',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                          { title: 'Dashboard' }
                        ]
                      },
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                    component: EmprestimoFormComponent,
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule 
                ], 
                declarations: [ 
                EmprestimoGridComponent,
                EmprestimoFormComponent,
                ], 
                entryComponents: [EmprestimoGridComponent,EmprestimoFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class EmprestimoModule {}
