import { TestBed } from '@angular/core/testing';

import { ContasBancariasService } from './contas-bancarias.service';

describe('ContasBancariasService', () => {
  let service: ContasBancariasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContasBancariasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
