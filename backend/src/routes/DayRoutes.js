const express = require("express");
const { createDay } = require("../services/DayService");
const app = express()
app.use(express.json())


app.post("add-day", (req,res)=>{
    const day = createDay(req.body)
    res.sendStatus(200)
})

app.get("get-day", (req,res)=>{
    const userID = req.body['userID'];
    const day = req.body["day"]
})