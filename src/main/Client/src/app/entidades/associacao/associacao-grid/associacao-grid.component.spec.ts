import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { AgenciaGridComponent } from './agencia-grid.component'; 

describe('AgenciaGridComponent', () => { 
     let component: AgenciaGridComponent; 
     let fixture: ComponentFixture<AgenciaGridComponent>; 

     beforeEach(async(() => { 
          TestBed.configureTestingModule({ 
          declarations: [ AgenciaGridComponent ] 
         }) 
         .compileComponents(); 
     })); 

     it('should create', () => { 
          expect(component).toBeTruthy(); 
     }); 
}); 
