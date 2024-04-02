const { Timestamp } = require("mongodb");
const fdc = require("../services/FDCService")
const User = require("../models/user");
const Food = require("../models/food");



async function addUser(json){

    const newUser = User(json)
    await newUser.save();
    
}



module.exports = {
    addUser,
}