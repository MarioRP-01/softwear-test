import { ProductSize } from "../data-type/product-size";

export interface Product {
    id: number;
    name: string;
    description: string;   
    price: number;
    stock: number;
    size: string;
    images: string[]
}
