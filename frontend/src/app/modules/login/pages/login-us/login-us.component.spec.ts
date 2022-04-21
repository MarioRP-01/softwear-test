import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginUsComponent } from './login-us.component';

describe('LoginUsComponent', () => {
  let component: LoginUsComponent;
  let fixture: ComponentFixture<LoginUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginUsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
