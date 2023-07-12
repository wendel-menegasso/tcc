import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FalhaDeLoginComponent } from './falha-de-login.component';

describe('FalhaDeLoginComponent', () => {
  let component: FalhaDeLoginComponent;
  let fixture: ComponentFixture<FalhaDeLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FalhaDeLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FalhaDeLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
