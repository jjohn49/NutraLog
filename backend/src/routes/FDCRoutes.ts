import express from "express"
import { getFood } from "../services/FDCService";
import { Food } from "../models/food";


const app = express()
app.use(express.json());

app.get("/food/:foodID", async (req,res)=>{

    const foodID: String = req.params.foodID;
    
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