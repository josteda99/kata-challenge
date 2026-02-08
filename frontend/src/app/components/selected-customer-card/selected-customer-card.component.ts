import { Component, input, output } from '@angular/core';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { Account, CustomerWithAccount } from '../../models/bank.models';

@Component({
  selector: 'app-selected-customer-card',
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatListModule,
  ],
  templateUrl: './selected-customer-card.component.html',
})
export class SelectedCustomerCardComponent {
  public accountForm = input.required<FormGroup>();
  public selectedCustomer = input.required<CustomerWithAccount | null>();
  public accountInfo = input.required<Account | null>();

  public create = output<void>();

  public onSubmit() {
    this.create.emit();
  }
}
