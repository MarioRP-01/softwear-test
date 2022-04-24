import { Transaction } from "./transaction";


export interface PageableTransaction {

    transactions: Transaction[];
    totalPages: number;
}
