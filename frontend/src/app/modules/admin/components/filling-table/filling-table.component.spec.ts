import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FillingTableComponent } from './filling-table.component';

describe('FillingTableComponent', () => {
  let component: FillingTableComponent;
  let fixture: ComponentFixture<FillingTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FillingTableComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FillingTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
