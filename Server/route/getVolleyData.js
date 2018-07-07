const express = require('express');
const router = express.Router();
const fs = require('fs');
router.post('/',(req, res)=>{
   let groupName = req.body;
   let savePath = "./music/test.mp3";
   console.log(toHexString(groupName['musicFile']));
   fs.writeFile(savePath, toHexString(groupName['musicFile']) ,null, (err, data)=>{

   });
   res.send({'soccerTeam':'Arsenal'})
});

toHexString = (byteArray)=>{
    return Array.from(byteArray, function(byte) {
        return ('0' + (byte & 0xFF).toString(16)).slice(-2);
    }).join('')
};
module.exports = router;