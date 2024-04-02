import { Day } from "../models/day";


export async function createDay(json: Object){
    const day = new Day(json)
    day.save()
}

async function updateDay(){

}

async function removeFood(){

}

async function addFood(){

}

