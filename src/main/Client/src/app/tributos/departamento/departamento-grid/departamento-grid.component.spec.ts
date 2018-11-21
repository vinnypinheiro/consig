import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { DepartamentoGridComponent } from './departamento-grid.component'; 

describe('DepartamentoGridComponent', () => { 
     let component: DepartamentoGridComponent; 
     let fixture: ComponentFixture<DepartamentoGridComponent>; 

     beforeEach(async(() => { 
          TestBed.configureTestingModule({ 
          declarations: [ DepartamentoGridComponent ] 
         }) 
         .compileComponents(); 
     })); 

     it('should create', () => { 
          expect(component).toBeTruthy(); 
     }); 
}); 
