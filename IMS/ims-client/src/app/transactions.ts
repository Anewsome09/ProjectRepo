export interface Transaction {
    stockId?: number;
    productId: number;
    transactionDate: Date;
    vendor: string;
    batchCode: string;
    invoiceNum: number;
    quantity: number;
    transactionType: string;
}