import { Product } from "./product";

export interface PageableProduct {
    products: Product[];
    totalPages: number;
}
