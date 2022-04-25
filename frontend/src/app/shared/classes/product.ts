import { Product as ProductInterface} from "../model";

export class Product implements ProductInterface{
    
    constructor(
        public id: number,
        public name: string,
        public description: string,
        public price: string,
        public size: string,
        public images: string[]

    ) { }

    

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
