import { inject, Injectable } from '@angular/core';
import {
  Account,
  Customer,
  CustomerWithAccount,
  NewAccount,
  NewCustomer,
} from '../models/bank.models';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BankApiService {
  private http = inject(HttpClient);
  private baseUrl = environment.apiUrl;

  public loadCustomers() {
    return this.http.get<CustomerWithAccount[]>(`${this.baseUrl}/customers`);
  }

  public createCustomer(newCustomer: NewCustomer) {
    return this.http.post<CustomerWithAccount>(`${this.baseUrl}/customers`, newCustomer);
  }

  public checkAccountInformation(customerId: string) {
    return this.http.get<CustomerWithAccount | null>(
      `${this.baseUrl}/accounts/customer/${customerId}`,
    );
  }

  public createAccount(newAccount: NewAccount) {
    return this.http.post<CustomerWithAccount>(`${this.baseUrl}/accounts`, newAccount);
  }
}
