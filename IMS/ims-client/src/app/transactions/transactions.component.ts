import { Component, OnInit } from '@angular/core';
import { TransactionService } from '../transactions.service';
import { Transaction } from '../transactions';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  transactions: Transaction[] = [];

  constructor(private transactionService: TransactionService) { }

ngOnInit(): void {
this.getTransactions();
}

  getTransactions() {
  this.transactionService.getAllTransactions()
  .subscribe(transactions => {
    this.transactions = transactions;
  })
}

}
