const { Timestamp } = require("mongodb");
const fdc = require("../services/FDCService")
const User = require("../models/user");
const Food = require("../models/food");



async function addUser(json){

    const newUser = User(json)
    await newUser.save();
    
}

async function addFoodToUser(userID,foodID){
    const query = await User.findOneAndUpdate({username:userID},{
        $push : {foods: [{
            foodID: foodID,
            time: Date()
        }]}
    })

    if(Food.exists({fdcId: foodID}) == null){
        const newFood = await fdc.getFood(foodID);
        await newFood.save();
    }

    await query.save()
    
}

module.exports = {
    addUser,
    addFoodToUser
}