const express = require("express");
const { addUser, addFoodToUser } = require("../services/UserService");
const app = express()
app.use(express.json())


app.post("/add-user", (req,res)=>{
    try{
        addUser(req.body);
        res.send(200)
    }catch(err){
        res.status(500).send(err);
    }
})

app.put("/add-food", async (req, res)=>{
    try{
        //console.log(req.body);
        const userID = req.body["userID"];
        const foodID = req.body["foodID"];
        await addFoodToUser(userID,foodID);
        res.sendStatus(200)
    }catch(err){
        console.log(err);
    }
})

app.get("/get-food", async (req, res)=>{

})


module.exports = app;