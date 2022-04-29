import { Coupon, Product, ProductInTransaction, ShopUser, Transaction } from "../model";

export class Wishlist implements Transaction{

    id: number;
    type: string;
    user: ShopUser;
    usedCoupon: Coupon;
    date: string;
    totalPrice: number;
    products: Product[];
    productsInTransaction: ProductInTransaction[] = [];

    constructor(transaction: Transaction) {

        this.id = transaction.id;
        this.type = transaction.type;
        this.user = transaction.user;
        this.usedCoupon = transaction.usedCoupon;
        this.date = transaction.date;
        this.totalPrice = transaction.totalPrice;
        this.products = transaction.products;
        this.initializeProductInTransaction();
    }

    private initializeProductInTransaction(): void {

        for (const product of this.products) {
        
            let productInTransaction = {
                id: product.id,
                name: product.name,
                size: product.size,
                price: product.price,
                quantity: 1
            }
    
            let position = this.productIsPresent(productInTransaction) 
            if (position == -1) {
                this.productsInTransaction.push(productInTransaction)
    
            } else {
                this.productsInTransaction[position].quantity++
            }
        }
    }       
    
    productIsPresent(product: ProductInTransaction): number {

        let isContained = false;
    
        let productIndex = 0;
        if (this.productsInTransaction.length !== productIndex) {
    
            while ((productIndex < this.productsInTransaction.length) && !isContained) {
    
            isContained = this.productsInTransaction[productIndex].name == product.name;
            if (!isContained)
                productIndex++
            }
    
        }
    
        if (!isContained)
            productIndex = -1
    
        return productIndex
    }
}
