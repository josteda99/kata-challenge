import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedCustomerCardComponent } from './selected-customer-card.component';

describe('SelectedCustomerCardComponent', () => {
  let component: SelectedCustomerCardComponent;
  let fixture: ComponentFixture<SelectedCustomerCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectedCustomerCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectedCustomerCardComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
