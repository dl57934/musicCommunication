const express = require('express');
const router = express.Router();
const fs = require('fs');
router.post('/',(req, res)=>{
   let groupName = req.body;
   let savePath = "./music/test.mp3";
   console.log(groupName['musicFile'].toString(16));

   res.send({'soccerTeam':'Arsenal'})
});

var hexChar = ["0", "1", "2", "3", "4", "5", "6", "7","8", "9", "A", "B", "C", "D", "E", "F"];

function byteToHex(b) {
    return hexChar[(b >> 4) & 0x0f] + hexChar[b & 0x0f];
}
module.exports = router;