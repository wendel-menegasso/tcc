import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrigemComponent } from './origem.component';

describe('OrigemComponent', () => {
  let component: OrigemComponent;
  let fixture: ComponentFixture<OrigemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrigemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrigemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
