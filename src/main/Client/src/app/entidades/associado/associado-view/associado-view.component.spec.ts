import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociadoViewComponent } from './associado-view.component';

describe('AssociadoViewComponent', () => {
  let component: AssociadoViewComponent;
  let fixture: ComponentFixture<AssociadoViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociadoViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociadoViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
