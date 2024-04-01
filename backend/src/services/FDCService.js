const axios = require("axios");

const { Food } = require("../models/food");

const API_KEY = "fqb4Fojb2agvj6XQOiYX86LtYJPvqXCYlAv4oTsa";

async function getFood(foodID){
    var food;
    await axios.get("https://api.nal.usda.gov/fdc/v1/food/" + foodID + "?api_key=fqb4Fojb2agvj6XQOiYX86LtYJPvqXCYlAv4oTsa")
    .then((res)=>{
        food = new Food(res.data)
    });

    return food;
}


module.exports = {
    getFood
}