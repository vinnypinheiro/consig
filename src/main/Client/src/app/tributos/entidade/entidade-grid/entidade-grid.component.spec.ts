import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { EntidadeGridComponent } from './entidade-grid.component'; 

describe('EntidadeGridComponent', () => { 
     let component: EntidadeGridComponent; 
     let fixture: ComponentFixture<EntidadeGridComponent>; 

     beforeEach(async(() => { 
          TestBed.configureTestingModule({ 
          declarations: [ EntidadeGridComponent ] 
         }) 
         .compileComponents(); 
     })); 

     it('should create', () => { 
          expect(component).toBeTruthy(); 
     }); 
}); 
