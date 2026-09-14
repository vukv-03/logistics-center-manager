import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutboundOrderTable } from './outbound-order-table';

describe('OutboundOrderTable', () => {
  let component: OutboundOrderTable;
  let fixture: ComponentFixture<OutboundOrderTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OutboundOrderTable],
    }).compileComponents();

    fixture = TestBed.createComponent(OutboundOrderTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
