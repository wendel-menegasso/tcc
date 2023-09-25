import { TestBed } from '@angular/core/testing';

import { OrigensService } from './origens.service';

describe('OrigensService', () => {
  let service: OrigensService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrigensService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
