import { Product as ProductInterface} from "../model";

export class Product implements ProductInterface{
    
    id: number;
    name: string;
    description: string;
    price: number;
    stock: number;
    size: string;
    images: string[];
    

    constructor(productInterface: ProductInterface) {

        this.id = productInterface.id;
        this.name = productInterface.name;
        this.description = productInterface.description;
        this.price = productInterface.price;
        this.stock = productInterface.stock;
        this.size = productInterface.size;
        this.images = productInterface.images;
     }
    
    containsProduct(collection: ProductInterface[]): boolean {

        let isContained = false;

        let productIndex = 0;
        if (collection.length !== productIndex) {

            while (productIndex < collection.length && !isContained) {

                isContained = collection[productIndex].name == this.name;
                productIndex++;
            }
        }
        
        return isContained;
    }
}
