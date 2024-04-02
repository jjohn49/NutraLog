const express = require("express");
const app = express();
const mongoose = require("mongoose")
app.use(express.json())


const DaySchema = mongoose.Schema({
    date: Date,
    userID: {type: mongoose.Schema.ObjectId, ref: "User"},
    calorieGoal:Number,
    proteinGoal:Number,
    carbGoal:Number,
    fatGoal:Number,
    foodsConsumed:[
        {type: mongoose.Schema.ObjectId, ref: "food"}
    ]
})

const Day = mongoose.model("Day",DaySchema);

module.exports = Day;


