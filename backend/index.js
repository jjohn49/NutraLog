const express = require("express");
const mongoose = require("mongoose")
const FDCRouter = require("./src/routes/FDCRoutes")
const UserRouter = require("./src/routes/UserRoutes")
require("dotenv").config()

const app = express();
app.use(express.json())
app.use(FDCRouter);
app.use(UserRouter);


mongoose.connect(process.env.DB_CONNECT_STRING);

const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error: "));
db.once("open", function(){
    console.log("Connection to Database was successful")
})

app.listen(process.env.PORT, () => console.log("Server is running"));