const User = require("../models/user");


async function addUser(json){

    const newUser = User(json)
    await newUser.save();
    
}

async function addFoodToUser(){

}

module.exports = {
    addUser
}