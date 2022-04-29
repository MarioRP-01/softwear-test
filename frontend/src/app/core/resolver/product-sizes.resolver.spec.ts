import { TestBed } from '@angular/core/testing';

import { ProductSizesResolver } from './product-sizes.resolver';

describe('ProductSizesResolver', () => {
  let resolver: ProductSizesResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(ProductSizesResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
