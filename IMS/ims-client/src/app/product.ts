export interface Product {
//export class Product implements IProduct{
    // making each param public is typescript shorthand to create a public 
    // field and assign a value every time a Product is constructed
    productId: number;
    title: string;
    category: string;
    man: string;
    minLimit: number;
    productQuantity: number;

    quantity: number;
    vendor: string;
    batchCode: string;
    invoiceNumber: string;


}

