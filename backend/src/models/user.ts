import { Model, Schema } from "mongoose";

const mongoose = require("mongoose");
const uniqueValidator = require("mongoose-unique-validator")


const UserSchema: Schema = mongoose.Schema({
    username: {type: String, lowercase: true, unique: true, required: [true, "can't be blank"], match: [/^[a-zA-Z0-9]+$/, 'is invalid'], index: true},
    email: {type: String, lowercase: true, unique: true, required: [true, "can't be blank"], match: [/\S+@\S+\.\S+/, 'is invalid'], index: true},
    //need to add more stuff here later on
})

UserSchema.plugin(uniqueValidator, {message: "user is already taken"});

export const User = mongoose.model("User", UserSchema);

