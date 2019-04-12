import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociacaoViewComponent } from './associacao-view.component';

describe('AssociacaoViewComponent', () => {
  let component: AssociacaoViewComponent;
  let fixture: ComponentFixture<AssociacaoViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AssociacaoViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociacaoViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
