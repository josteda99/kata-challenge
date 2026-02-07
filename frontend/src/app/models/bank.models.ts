export interface Customer {
  id: string;
  documentType: DocType;
  documentNumber: string;
  fullName: string;
  email: string;
}

export interface Account {
  id: string;
  customer: Customer;
  accountNumber: string;
  status: Status;
}

export type DocType = 'CC' | 'CE' | 'PAS';
export type Status = 'ACTIVE' | 'INACTIVE';

export interface NewCustomer {
  documentType: DocType;
  documentNumber: string;
  fullName: string;
  email: string;
}
