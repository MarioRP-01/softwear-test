import { TestBed } from '@angular/core/testing';

import { UserLoggedResolver } from './user-logged.resolver';

describe('UserLoggedResolver', () => {
  let resolver: UserLoggedResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    resolver = TestBed.inject(UserLoggedResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
