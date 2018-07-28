const express = require('express');
const router = express.Router();
const createModel = require('../model/connectMongoDB');
const saveUser = require('../model/relativeUserInfo');
const userInfo = new createModel();
let model = userInfo.createUserSchema();

router.get('/', (data)=>{
    saveUser(model, data);
});

module.exports = router; 