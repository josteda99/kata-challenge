import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { ErrorStore } from '../store/error.store';

export const httpErrorInterceptor: HttpInterceptorFn = (req, next) => {
  const errorStore = inject(ErrorStore);

  return next(req).pipe(
    catchError((error: HttpErrorResponse) => {
      let message = 'Something is wrong';

      if (error.status === 401) {
        message = 'Unauthorized — please log in again';
      }

      if (error.status === 409) {
        message = 'This customer already exists';
      }

      if (error.status === 403) {
        message = 'You do not have permission to perform this action';
      }

      if (error.status === 500) {
        message = 'Internal server error';
      }

      errorStore.setError(message);

      return throwError(() => error);
    }),
  );
};
