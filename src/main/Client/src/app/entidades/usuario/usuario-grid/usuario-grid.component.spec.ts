import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { UsuarioGridComponent } from './usuario-grid.component'; 

describe('UsuarioGridComponent', () => { 
     let component: UsuarioGridComponent; 
     let fixture: ComponentFixture<UsuarioGridComponent>; 

     beforeEach(async(() => { 
          TestBed.configureTestingModule({ 
          declarations: [ UsuarioGridComponent ] 
         }) 
         .compileComponents(); 
     })); 

     it('should create', () => { 
          expect(component).toBeTruthy(); 
     }); 
}); 
