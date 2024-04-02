import express from "express"
import mongoose from "mongoose"
import dotenv from "dotenv"
dotenv.config();

const db_connect: string = process.env.DB_CONNECT_STRING ?? "";

const FDCRouter = require("./src/routes/FDCRoutes")
const UserRouter = require("./src/routes/UserRoutes")


const app = express();
app.use(express.json())
app.use(FDCRouter);
app.use(UserRouter);


mongoose.connect(db_connect);

const db = mongoose.connection;
db.on("error", console.error.bind(console, "connection error: "));
db.once("open", function(){
    console.log("Connection to Database was successful")
})

app.listen(process.env.PORT, () => console.log("Server is running"));