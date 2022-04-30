import { TestBed } from '@angular/core/testing';

import { IncomesResolver } from './incomes.resolver';

describe('IncomesResolver', () => {
  let resolver: IncomesResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(IncomesResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
