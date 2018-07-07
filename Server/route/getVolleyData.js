const express = require('express');
const router = express.Router();
const fs = require('fs');
router.post('/',(req, res)=>{
   let groupName = req.body;
   let savePath = "./music/test.mp3";
   let data = groupName['musicFile'];
   console.log(groupName['musicFile']);
   fs.writeFile(savePath, encodeURI(data), null, ()=>{

   });
   res.send({'soccerTeam':'Arsenal'})
});

module.exports = router;