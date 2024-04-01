const express = require("express");
const { getFood } = require("../services/FDCService");
const Food = require("../models/food");
const app = express()
app.use(express.json());

app.get("/food/:foodID", async (req,res)=>{
    var food;
    food = await Food.exists({fdcId:foodID})

    if(food != null){
        res.send(food).sendStatus(200);
        return;
    }
    
    food = await getFood(req.params["foodID"]);
    res.send(food).status(200)
})

module.exports = app;