const express = require('express');
const router = express.Router();
const fs = require('fs');
const multiParty = require('multiparty');

router.post('/',(req, res)=>{
   let payload = {
      maxBytes:209715200,
       output:'stream',
       parse:false
   };

   let multiparty = require('multiparty');
   let form = new multiparty.Form();

   form.parse(req, (err, fields, files)=>{
      console.log(err);
      console.log(fields);
      console.log(files);
   });

   console.log(req.body);
   res.send({'soccerTeam':'Arsenal'})
});

module.exports = router;