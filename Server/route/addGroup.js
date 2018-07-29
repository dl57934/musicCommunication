let express = require('express');
const router = express.Router();
const createModel = require('../model/connectMongoDB');
let savingGroup = require('../model/saveGroup');
const groupInfo = new createModel();
let model = groupInfo.createGroupSchema();
router.post('/', (req, res)=>{
    console.log('get Data', req.body);
    savingGroup(model, req.body);
});

module.exports = router;