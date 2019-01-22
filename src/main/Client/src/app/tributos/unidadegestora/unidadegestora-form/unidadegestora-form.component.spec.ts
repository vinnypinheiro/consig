import { async, ComponentFixture, TestBed } from '@angular/core/testing'; 
import { UnidadeGestoraFormComponent } from './unidadegestora-form.component'; 

describe('UnidadeGestoraFormComponent', () => { 
     let component: UnidadeGestoraFormComponent; 
     let fixture: ComponentFixture<UnidadeGestoraFormComponent>; 
     beforeEach(async(() => { 
         TestBed.configureTestingModule({ 
             declarations: [ UnidadeGestoraFormComponent ] 
         }) 
         .compileComponents(); 
     }));
     beforeEach(() => {
         fixture = TestBed.createComponent(UnidadeGestoraFormComponent); 
         component = fixture.componentInstance; 
         fixture.detectChanges(); 
     }); 
     it('should create', () => { 
         expect(component).toBeTruthy(); 
     }); 
}); 
