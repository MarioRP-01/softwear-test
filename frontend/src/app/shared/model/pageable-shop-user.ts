import { ShopUser } from "./shop-user";

export interface PageableShopUser {

    users: ShopUser[];
    totalPages: number;
}
