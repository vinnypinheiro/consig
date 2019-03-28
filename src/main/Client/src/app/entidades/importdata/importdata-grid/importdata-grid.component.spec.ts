import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { importdataGridComponent } from './importdata-grid.component';

describe('AgenciaGridComponent', () => { 
     let component: importdataGridComponent;
     let fixture: ComponentFixture<importdataGridComponent>;

     beforeEach(async(() => { 
          TestBed.configureTestingModule({ 
          declarations: [ importdataGridComponent ]
         }) 
         .compileComponents(); 
     })); 

     it('should create', () => { 
          expect(component).toBeTruthy(); 
     }); 
}); 
