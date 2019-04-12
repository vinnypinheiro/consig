import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CorrespondenteViewComponent } from './correspondente-view.component';

describe('CorrespondenteViewComponent', () => {
  let component: CorrespondenteViewComponent;
  let fixture: ComponentFixture<CorrespondenteViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CorrespondenteViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CorrespondenteViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
