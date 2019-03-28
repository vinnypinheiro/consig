import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportdataViewComponent } from './importdata-view.component';

describe('ImportdataViewComponent', () => {
  let component: ImportdataViewComponent;
  let fixture: ComponentFixture<ImportdataViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImportdataViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportdataViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
