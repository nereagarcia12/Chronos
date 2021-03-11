import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionReceivedComponent } from './transaction-received.component';

describe('TransactionReceivedComponent', () => {
  let component: TransactionReceivedComponent;
  let fixture: ComponentFixture<TransactionReceivedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionReceivedComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionReceivedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
