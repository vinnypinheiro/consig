import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { SocioFormComponent } from './socio-form.component'; 

describe('SocioFormComponent', () => { 
     let component: SocioFormComponent; 
     let fixture: ComponentFixture<SocioFormComponent>; 
     beforeEach(async(() => { 
         TestBed.configureTestingModule({ 
             declarations: [ SocioFormComponent ] 
         }) 
         .compileComponents(); 
     }));
     beforeEach(() => {
         fixture = TestBed.createComponent(SocioFormComponent); 
         component = fixture.componentInstance; 
         fixture.detectChanges(); 
     }); 
     it('should create', () => { 
         expect(component).toBeTruthy(); 
     }); 
}); 
