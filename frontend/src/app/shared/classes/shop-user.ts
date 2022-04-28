import { ShopUser as ShopUserInterface } from "../model";

export class ShopUser implements ShopUserInterface{
    
    id: number;
    username: string;
    email: string;
    name: string;
    lastName: string;
    password: string;
    address: string;
    mobileNumber: number;
    birthdate: string;
    role: string;

    constructor(shopUser: ShopUserInterface) {

        this.id = shopUser.id;
        this.username = shopUser.username;
        this.email = shopUser.email;
        this.name = shopUser.name;
        this.lastName = shopUser.lastName;
        this.password = shopUser.password;
        this.address = shopUser.address;
        this.mobileNumber = shopUser.mobileNumber;
        this.birthdate = shopUser.birthdate;
        this.role = shopUser.role;
    }
}
