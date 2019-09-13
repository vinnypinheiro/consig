import {RouterModule, Routes} from '@angular/router'; 
 
import {NgModule} from '@angular/core';
import {SharedModule} from '../../shared/shared.module';
import {SetorComponent} from "./sertor.component";
import {SetorGridComponent} from "./setor-grid/setor-grid.component";

const routes: Routes = [
  { path: '', component: SetorGridComponent },

  { path: ':id',  component: SetorGridComponent },


];

 
        @NgModule({ 
                imports: [RouterModule.forChild(routes),
                SharedModule 
                ], 
                declarations: [
                  SetorComponent,
                  SetorGridComponent
                ], 
                entryComponents: [SetorComponent],
                schemas: [], 
                providers: [] 
                }) 
        export class SetorModule {}
