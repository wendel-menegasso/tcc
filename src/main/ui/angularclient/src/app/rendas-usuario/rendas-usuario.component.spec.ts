import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RendasUsuarioComponent } from './rendas-usuario.component';

describe('RendasUsuarioComponent', () => {
  let component: RendasUsuarioComponent;
  let fixture: ComponentFixture<RendasUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RendasUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RendasUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
