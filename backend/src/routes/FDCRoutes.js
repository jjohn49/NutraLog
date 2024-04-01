const express = require("express");
const { getFood } = require("../services/FDCService");
const app = express()
app.use(express.json());

app.get("/food/:foodID", async (req,res)=>{
    
    let food = await getFood(req.params["foodID"]);
    res.send(food).status(200)
})

module.exports = app;