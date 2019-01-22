import { RouterModule } from '@angular/router'; 
 
import {ParcelaGridComponent} from './parcela-grid/parcela-grid.component';
import {ParcelaFormComponent} from './parcela-form/parcela-form.component';
import {AuthGuard} from '../../security/auth.guard'; 
import {NgModule} from '@angular/core'; 
import {ParcelaService} from './parcela.service';
import {SharedModule} from '../../shared/shared.module'; 
 
const ROUTES  = [ 
        { 
            path: '', 
                    component: ParcelaGridComponent,
                    data: {
                        title: 'Cadastro de Parcela',
                        urls: [
                          { title: 'Dashboard', url: '/dashboard' },
                          { title: 'Dashboard' }
                        ]
                      },
                canActivate: [AuthGuard] 
        }, 
        { 
            path: ':id', 
                    component: ParcelaFormComponent,
                canActivate: [AuthGuard] 
        }]; 
 
        @NgModule({ 
                imports: [RouterModule.forChild(ROUTES), 
                SharedModule 
                ], 
                declarations: [ 
                ParcelaGridComponent,
                ParcelaFormComponent,
                ], 
                entryComponents: [ParcelaGridComponent,ParcelaFormComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class ParcelaModule {}
