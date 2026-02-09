import { Component, input, output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { CustomerWithAccount } from '../../models/bank.models';

@Component({
  selector: 'app-customers-table',
  imports: [MatTableModule, MatCardModule],
  templateUrl: './customers-table.component.html',
  styles: `
    .table-wrapper {
      width: 100%;
      overflow-x: auto;
    }

    .table-wrapper table {
      min-width: 800px;
    }
  `,
})
export class CustomersTableComponent {
  public columns = input.required<string[]>();
  public customers = input.required<CustomerWithAccount[]>();

  public select = output<string>();

  public onSubmit(id: string) {
    this.select.emit(id);
  }
}
