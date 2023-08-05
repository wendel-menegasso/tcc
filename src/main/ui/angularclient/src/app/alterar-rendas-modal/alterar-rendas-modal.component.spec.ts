import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterarRendasModalComponent } from './alterar-rendas-modal.component';

describe('AlterarRendasModalComponent', () => {
  let component: AlterarRendasModalComponent;
  let fixture: ComponentFixture<AlterarRendasModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlterarRendasModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlterarRendasModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
