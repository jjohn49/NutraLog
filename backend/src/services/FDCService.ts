import axios, { AxiosResponse } from "axios";

import { Food } from "../models/food";

const API_KEY = "fqb4Fojb2agvj6XQOiYX86LtYJPvqXCYlAv4oTsa";

export async function getFood(foodID: String){
    var food;
    await axios.get("https://api.nal.usda.gov/fdc/v1/food/" + foodID + "?api_key=fqb4Fojb2agvj6XQOiYX86LtYJPvqXCYlAv4oTsa")
    .then((res: AxiosResponse)=>{
        food = new Food(res.data)
    });

    return food;
}