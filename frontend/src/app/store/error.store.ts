import { signal, Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class ErrorStore {
  private _error = signal<string | null>(null);

  public error = this._error.asReadonly();

  setError(msg: string) {
    this._error.set(msg);
  }

  clearError() {
    this._error.set(null);
  }
}
