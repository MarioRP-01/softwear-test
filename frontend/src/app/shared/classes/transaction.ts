import { ShopUser, Coupon, Product, ProductInTransaction } from '../model';
import {Transaction as TransactionInterface} from '../model/transaction'

export class Transaction implements TransactionInterface{

    id: number;
    type: string;
    user: ShopUser;
    usedCoupon: Coupon;
    date: string;
    totalPrice: number;
    products: Product[];
    productsInTransaction: ProductInTransaction[];

    constructor(transactionInterface: TransactionInterface) {

        this.id = transactionInterface.id;
        this.type = transactionInterface.type;
        this.user = transactionInterface.user;
        this.usedCoupon = transactionInterface.usedCoupon;
        this.date = transactionInterface.date;
        this.totalPrice = transactionInterface.totalPrice;
        this.products = transactionInterface.products;
        this.productsInTransaction = this.initializeProductInTransaction();
    }

    private initializeProductInTransaction(): ProductInTransaction[] {

        const productsInTransaction: ProductInTransaction[] = [];

        this.products.forEach(
            product => {
        
                let productInTransaction = {
                  id: product.id,
                  name: product.name,
                  size: product.size,
                  price: product.price,
                  quantity: 1
                }
        
                let position = this.productIsPresent(productInTransaction) 
                if ( position == -1) {
                    productsInTransaction.push(productInTransaction)
        
                } else {
                    productsInTransaction[position].quantity++
                }
            }
        )

        return productsInTransaction;
    }       
    
    productIsPresent(product: ProductInTransaction): number {

        let isContained = false;
    
        let productIndex = 0;
        if (this.products.length !== productIndex) {
    
            while ((productIndex < this.products.length) && !isContained) {
    
            isContained = this.products[productIndex].id == product.id;
            if (!isContained)
                productIndex++
            }
    
        }
    
        if (!isContained)
            productIndex = -1
    
        return productIndex
    }
}

    
