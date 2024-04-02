import express from "express";
const app = express();
import mongoose, { Document, Model, Schema } from "mongoose";
app.use(express.json())


const DaySchema: Schema = new mongoose.Schema({
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

export const Day = mongoose.model("Day",DaySchema);




