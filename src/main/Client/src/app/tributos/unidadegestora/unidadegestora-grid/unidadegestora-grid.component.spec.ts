import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { UnidadeGestoraGridComponent } from './unidadegestora-grid.component'; 

describe('UnidadeGestoraGridComponent', () => { 
     let component: UnidadeGestoraGridComponent; 
     let fixture: ComponentFixture<UnidadeGestoraGridComponent>; 

     beforeEach(async(() => { 
          TestBed.configureTestingModule({ 
          declarations: [ UnidadeGestoraGridComponent ] 
         }) 
         .compileComponents(); 
     })); 

     it('should create', () => { 
          expect(component).toBeTruthy(); 
     }); 
}); 
