import { TestBed } from '@angular/core/testing';

import { AvailableSizesResolver } from './available-sizes.resolver';

describe('AvailableSizesResolver', () => {
  let resolver: AvailableSizesResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(AvailableSizesResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
