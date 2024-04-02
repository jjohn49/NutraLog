import {User} from "../models/user"

export async function addUser(json: Object){

    const newUser = User(json)
    await newUser.save();
    
}
