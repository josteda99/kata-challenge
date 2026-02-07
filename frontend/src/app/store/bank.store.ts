import { NewCustomer } from './../models/bank.models';
import { Customer } from '../models/bank.models';
import { patchState, signalStore, withMethods, withState } from '@ngrx/signals';
import { rxMethod } from '@ngrx/signals/rxjs-interop';
import { pipe, switchMap, tap } from 'rxjs';
import { BankApiService } from '../services/bank.api.service';
import { inject } from '@angular/core';
import { tapResponse } from '@ngrx/operators';

interface BankState {
  customers: Customer[];
  isLoading: boolean;
}

const initialState: BankState = {
  customers: [],
  isLoading: false,
};

export const BankStore = signalStore(
  withState(initialState),
  withMethods((store, bankService = inject(BankApiService)) => ({
    loadCustomers: rxMethod<void>(
      pipe(
        tap(() => patchState(store, { isLoading: true })),
        switchMap(() => {
          return bankService.loadCustomers().pipe(
            tapResponse({
              next: (customers) => patchState(store, { customers, isLoading: false }),
              error: (err) => {
                patchState(store, { isLoading: false });
                console.error(err);
              },
            }),
          );
        }),
      ),
    ),
    createCustomer: rxMethod<NewCustomer>(
      pipe(
        tap(() => patchState(store, { isLoading: true })),
        switchMap((newCustomer) => {
          return bankService.createCustomer(newCustomer).pipe(
            tapResponse({
              next: (customer) =>
                patchState(store, {
                  customers: [...store.customers(), customer],
                  isLoading: false,
                }),
              error: (err) => {
                patchState(store, { isLoading: false });
                console.error(err);
              },
            }),
          );
        }),
      ),
    ),
  })),
);
