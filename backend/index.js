const express = require("express");
const mongoose = require("mongoose")
const FDCRouter = require("./src/routes/FDCRoutes")
const UserRouter = require("./src/routes/UserRoutes")

const app = express();
app.use(express.json())
app.use(FDCRouter);
app.use(UserRouter);


mongoose.connect(
    "mongodb+srv://john:Green2002@database.k24fjf8.mongodb.net/?retryWrites=true&w=majority&appName=Database");

const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error: "));
db.once("open", function(){
    console.log("Connection to Database was successful")
})


app.listen(3000, () => console.log("Server is running"));