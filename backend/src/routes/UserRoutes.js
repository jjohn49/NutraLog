const express = require("express");
const { addUser } = require("../services/UserService");
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


module.exports = app;