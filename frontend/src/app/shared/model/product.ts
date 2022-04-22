import { ProductSize } from "../data-type/product-size";

export interface Product {
    id: number;
    name: string;
    description: string;
    price: string;
    size: string;
    images: string[]
}
