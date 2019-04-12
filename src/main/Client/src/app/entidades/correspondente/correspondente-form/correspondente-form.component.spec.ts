import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { CorrespondenteFormComponent } from './correspondente-form.component'; 

describe('CorrespondenteFormComponent', () => { 
     let component: CorrespondenteFormComponent; 
     let fixture: ComponentFixture<CorrespondenteFormComponent>; 
     beforeEach(async(() => { 
         TestBed.configureTestingModule({ 
             declarations: [ CorrespondenteFormComponent ] 
         }) 
         .compileComponents(); 
     }));
     beforeEach(() => {
         fixture = TestBed.createComponent(CorrespondenteFormComponent); 
         component = fixture.componentInstance; 
         fixture.detectChanges(); 
     }); 
     it('should create', () => { 
         expect(component).toBeTruthy(); 
     }); 
}); 
