import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConvenioViewComponent } from './convenio-view.component';

describe('AssociacaoViewComponent', () => {
  let component: ConvenioViewComponent;
  let fixture: ComponentFixture<ConvenioViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConvenioViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConvenioViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
