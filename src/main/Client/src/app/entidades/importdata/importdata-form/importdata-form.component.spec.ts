import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { ImportdataFormComponent } from './importdata-form.component'; 

describe('ImportdataFormComponent', () => { 
     let component: ImportdataFormComponent; 
     let fixture: ComponentFixture<ImportdataFormComponent>; 
     beforeEach(async(() => { 
         TestBed.configureTestingModule({ 
             declarations: [ ImportdataFormComponent ] 
         }) 
         .compileComponents(); 
     }));
     beforeEach(() => {
         fixture = TestBed.createComponent(ImportdataFormComponent); 
         component = fixture.componentInstance; 
         fixture.detectChanges(); 
     }); 
     it('should create', () => { 
         expect(component).toBeTruthy(); 
     }); 
}); 
