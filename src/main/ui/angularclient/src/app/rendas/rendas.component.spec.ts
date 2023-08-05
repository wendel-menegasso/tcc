import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RendasComponent } from './rendas.component';

describe('RendasComponent', () => {
  let component: RendasComponent;
  let fixture: ComponentFixture<RendasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RendasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RendasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
