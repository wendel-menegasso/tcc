import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirRendasModalComponent } from './inserir-rendas-modal.component';

describe('InserirRendasModalComponent', () => {
  let component: InserirRendasModalComponent;
  let fixture: ComponentFixture<InserirRendasModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InserirRendasModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InserirRendasModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
