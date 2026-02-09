import { CustomerWithAccount, NewAccount, NewCustomer, Status } from './../models/bank.models';
import { patchState, signalStore, withComputed, withMethods, withState } from '@ngrx/signals';
import { rxMethod } from '@ngrx/signals/rxjs-interop';
import { of, pipe, switchMap, tap } from 'rxjs';
import { BankApiService } from '../services/bank.api.service';
import { computed, inject } from '@angular/core';
import { tapResponse } from '@ngrx/operators';

interface BankState {
  customers: CustomerWithAccount[];
  isLoading: boolean;
  selectedCustomer: CustomerWithAccount | null;
}

const initialState: BankState = {
  customers: [],
  isLoading: false,
  selectedCustomer: null,
};

export const BankStore = signalStore(
  withState(initialState),
  withComputed((store) => ({
    accoutInfo: computed(() => store.selectedCustomer()?.account ?? null),
  })),
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
    createAccount: rxMethod<Status>(
      pipe(
        tap(() => patchState(store, { isLoading: true })),
        switchMap((status) => {
          const selectedCustomerId = store.selectedCustomer()?.id;
          if (!selectedCustomerId)
            return of(
              patchState(store, {
                isLoading: false,
              }),
            );

          const newAccount: NewAccount = {
            status,
            customerId: selectedCustomerId,
          };

          return bankService.createAccount(newAccount).pipe(
            tapResponse({
              next: (customerUpdated) =>
                patchState(store, {
                  customers: [
                    ...store.customers().map((customer) =>
                      customer.id === customerUpdated.id
                        ? {
                            ...customer,
                            account: customerUpdated.account,
                            hasAccount: customerUpdated.hasAccount,
                          }
                        : customer,
                    ),
                  ],
                  selectedCustomer: customerUpdated,
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
    selectCustomer(customerId: string): void {
      patchState(store, {
        selectedCustomer: store.customers().find((customer) => customer.id === customerId),
      });
    },
  })),
);
