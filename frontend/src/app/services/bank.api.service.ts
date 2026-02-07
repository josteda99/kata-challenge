import { Injectable } from '@angular/core';
import { of } from 'rxjs';
import { Account, Customer, NewAccount, NewCustomer } from '../models/bank.models';

@Injectable({
  providedIn: 'root',
})
export class BankApiService {
  public loadCustomers() {
    return of<Customer[]>([
      {
        id: '1',
        documentType: 'CC',
        documentNumber: '1',
        fullName: 'johan',
        email: 'josteda99@gmail.com',
      },
    ]);
  }

  public createCustomer(newCustomer: NewCustomer) {
    return of<Customer>({
      id: crypto.randomUUID(),
      documentType: newCustomer.documentType,
      documentNumber: newCustomer.documentNumber,
      fullName: newCustomer.fullName,
      email: newCustomer.email,
    });
  }

  public checkAccountInformation(customerId: string) {
    return of<Account | null>(null);
  }

  public createAccount(newAccount: NewAccount) {
    return of<Account>({
      id: crypto.randomUUID(),
      customerId: newAccount.customerId,
      accountNumber: crypto.randomUUID(),
      status: newAccount.status,
    });
  }
}
