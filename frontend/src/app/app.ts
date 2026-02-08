import { Component, inject, OnInit, signal } from '@angular/core';
import { BankStore } from './store/bank.store';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { DocType, NewCustomer, Status } from './models/bank.models';
import { CustomerFormComponent } from './components/customer-form/customer-form.component';
import { SelectedCustomerCardComponent } from './components/selected-customer-card/selected-customer-card.component';
import { CustomersTableComponent } from './components/customers-table/customers-table.component';
@Component({
  selector: 'app-root',
  imports: [
    ReactiveFormsModule,
    CustomerFormComponent,
    SelectedCustomerCardComponent,
    CustomersTableComponent,
  ],
  providers: [BankStore],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  private readonly _store = inject(BankStore);
  private fb = inject(FormBuilder);

  public customers = this._store.customers;
  public isLoading = this._store.isLoading;
  public selectedCustomer = this._store.selectedCustomer;
  public accountInfo = this._store.accoutInfo;

  public columns = signal(['No', 'document', 'fullName', 'email', 'hasAccount']);

  public customerForm = this.fb.nonNullable.group({
    documentType: [<DocType>'CC'],
    documentNumber: ['', Validators.required],
    fullName: [''],
    email: ['', [Validators.required, Validators.email]],
  });

  public accountForm = this.fb.nonNullable.group({
    status: [<Status>'ACTIVE'],
  });

  ngOnInit() {
    this._store.loadCustomers();
  }

  public createCustomer() {
    const customerForm = this.customerForm;

    const newCustomer: NewCustomer = {
      documentType: customerForm.controls.documentType.value,
      documentNumber: customerForm.controls.documentNumber.value,
      fullName: customerForm.controls.fullName.value,
      email: customerForm.controls.email.value,
    };

    this._store.createCustomer(newCustomer);
  }

  public selectCustomer(customerId: string) {
    this._store.selectCustomer(customerId);
  }

  public createAccount() {
    this._store.createAccount(this.accountForm.controls.status.value);
  }
}
