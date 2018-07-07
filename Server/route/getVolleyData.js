const express = require('express');
const router = express.Router();
const fs = require('fs');
const multiParty = require('multiparty');

router.post('/',(req, res)=>{
   let groupName = req.body;
   let savePath = "./music/test.mp3";
   console.log(req.body);
   res.send({'soccerTeam':'Arsenal'})
});

module.exports = router;