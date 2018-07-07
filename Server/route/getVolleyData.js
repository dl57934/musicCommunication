const express = require('express');
const router = express.Router();
const fs = require('fs');
const multiParty = require('multiparty');

router.post('/',(req, res)=>{
   let payload = {

   };

   let multiparty = require('multiparty');
   let form = new multiparty.Form({
       autoFiles:false,
       uploadDir: 'music',
   });

   form.parse(req, (err, fields, files)=>{
      console.log(err);
      console.log(files);
      console.log(files);
   });

   console.log(req.body);
   res.send({'soccerTeam':'Arsenal'})
});

module.exports = router;