import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { AgenciaFormComponent } from './agencia-form.component'; 

describe('AgenciaFormComponent', () => { 
     let component: AgenciaFormComponent; 
     let fixture: ComponentFixture<AgenciaFormComponent>; 
     beforeEach(async(() => { 
         TestBed.configureTestingModule({ 
             declarations: [ AgenciaFormComponent ] 
         }) 
         .compileComponents(); 
     }));
     beforeEach(() => {
         fixture = TestBed.createComponent(AgenciaFormComponent); 
         component = fixture.componentInstance; 
         fixture.detectChanges(); 
     }); 
     it('should create', () => { 
         expect(component).toBeTruthy(); 
     }); 
}); 
