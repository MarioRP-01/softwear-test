import { ShopUser, Coupon, Product } from '../model';
import {Transaction as TransactionInterface} from '../model/transaction'

export class Transaction implements TransactionInterface{

    id: number;
    type: string;
    user: ShopUser;
    usedCoupon: Coupon;
    date: string;
    totalPrice: number;
    products: Product[];

    constructor(transactionInterface: TransactionInterface) {

        this.id = transactionInterface.id;
        this.type = transactionInterface.type;
        this.user = transactionInterface.user;
        this.usedCoupon = transactionInterface.usedCoupon;
        this.date = transactionInterface.date;
        this.totalPrice = transactionInterface.totalPrice;
        this.products = transactionInterface.products;
    }
}
