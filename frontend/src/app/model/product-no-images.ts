import { ProductSize } from "./data/product-size";

export interface ProductNoImages {
    id: number;
    name: string;
    description: string;
    price: string;
    size: ProductSize;
}
