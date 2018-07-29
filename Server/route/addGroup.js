let express = require('express');
let fs = require('fs');
const router = express.Router();

router.post('/', (req, res)=>{
    console.log('get Data', req.body['Image']);
});

module.exports = router;