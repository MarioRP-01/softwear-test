import { Coupon } from "./coupon";
import { Product } from "./product";
import { ShopUser } from "./shop-user";

export interface Transaction {
    id: number;
    type: string;
    user: ShopUser;
    usedCoupon: Coupon;
    date: string;
    totalPrice: number;
    products: Product[];
}
