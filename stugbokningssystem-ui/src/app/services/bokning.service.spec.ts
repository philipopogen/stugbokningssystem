import { TestBed } from '@angular/core/testing';

import { BokningService } from './bokning.service';

describe('BokningService', () => {
  let service: BokningService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BokningService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
