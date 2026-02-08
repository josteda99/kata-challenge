import { Component, inject, OnInit, signal } from '@angular/core';
import { BankStore } from './store/bank.store';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { DocType, NewCustomer, Status } from './models/bank.models';
@Component({
  selector: 'app-root',
  imports: [ReactiveFormsModule, MatFormFieldModule, MatInputModule],
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
