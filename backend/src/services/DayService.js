const { Timestamp } = require("mongodb");
const fdc = require("../services/FDCService")
const User = require("../models/user");
const Day = require("../models/day")


async function createDay(json){
    const day = new Day(json)
    day.save()
}

async function updateDay(){

}

async function removeFood(){

}

async function addFood(){

}

module.exports = {
    createDay,
    updateDay,
    addFood,
    removeFood
}