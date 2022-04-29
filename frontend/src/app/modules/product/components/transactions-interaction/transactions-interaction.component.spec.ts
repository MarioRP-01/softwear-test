import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionsInteractionComponent } from './transactions-interaction.component';

describe('TransactionsInteractionComponent', () => {
  let component: TransactionsInteractionComponent;
  let fixture: ComponentFixture<TransactionsInteractionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionsInteractionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionsInteractionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
