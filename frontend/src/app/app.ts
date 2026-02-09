import { Component, effect, inject, OnInit, signal } from '@angular/core';
import { BankStore } from './store/bank.store';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { DocType, NewCustomer, Status } from './models/bank.models';
import { CustomerFormComponent } from './components/customer-form/customer-form.component';
import { SelectedCustomerCardComponent } from './components/selected-customer-card/selected-customer-card.component';
import { CustomersTableComponent } from './components/customers-table/customers-table.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { ErrorStore } from './store/error.store';
@Component({
  selector: 'app-root',
  imports: [
    ReactiveFormsModule,
    CustomerFormComponent,
    SelectedCustomerCardComponent,
    CustomersTableComponent,
    MatProgressSpinnerModule,
  ],
  providers: [BankStore],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App implements OnInit {
  private readonly _store = inject(BankStore);
  private fb = inject(FormBuilder);
  private _snackBar = inject(MatSnackBar);
  private _errorStore = inject(ErrorStore);

  public customers = this._store.customers;
  public isLoading = this._store.isLoading;
  public selectedCustomer = this._store.selectedCustomer;
  public accountInfo = this._store.accoutInfo;

  public columns = signal(['No', 'document', 'fullName', 'email', 'hasAccount']);

  public customerForm = this.fb.nonNullable.group({
    documentType: [<DocType>'CC'],
    documentNumber: [
      '',
      [Validators.required, Validators.pattern('^[0-9]+$'), Validators.minLength(6)],
    ],
    fullName: [''],
    email: ['', [Validators.required, Validators.email, Validators.maxLength(150)]],
  });

  public accountForm = this.fb.nonNullable.group({
    status: [<Status>'ACTIVE'],
  });

  ngOnInit() {
    this._store.loadCustomers();
  }

  constructor() {
    effect(() => {
      const error = this._errorStore.error();

      if (error) {
        this._snackBar.open(error, 'Close', { duration: 3000 });
        this._errorStore.clearError();
      }
    });

    effect(() => {
      const data = this._store.createCustomerSuccess();
      if (data) {
        this._snackBar.open('Customer created succesfully', 'Close', { duration: 3000 });
        this.customerForm.reset();
        this.customerForm.controls.documentNumber.setErrors(null);
        this.customerForm.controls.email.setErrors(null);
        this._store.resetCustomerCreation();
      }
    });
  }

  public createCustomer() {
    const customerForm = this.customerForm;

    if (customerForm.invalid || customerForm.pristine) {
      this._snackBar.open('Form is invalid', '', { duration: 3000 });
      return;
    }

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
