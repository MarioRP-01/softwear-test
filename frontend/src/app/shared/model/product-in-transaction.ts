import { ProductSize } from "../data-type";

export interface ProductInTransaction {

    id: number;
    name: string;
    size: string;
    price: number;
    quantity: number;
}
