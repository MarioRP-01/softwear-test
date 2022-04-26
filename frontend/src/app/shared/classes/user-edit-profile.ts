import { UserEditProfile as UserEditProgileInterface } from "../model";

export class UserEditProfile implements UserEditProgileInterface{

    public name: string;
    public lastName: string;
    public address: string;
    public email: string;
    public mobileNumber: number;
    public birthdate: string;

    constructor() {

        this.name = "";
        this.lastName = "";
        this.address = "";
        this.email = "";
        this.mobileNumber = 0;
        this.birthdate = ""
    }
    

}
