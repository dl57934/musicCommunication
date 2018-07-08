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
      /*let folderName = files['audio'][0]['path'].substr(0,5);
      let fileName =  files['audio'][0]['path'].substr(6);

      fs.rename(folderName+'/'+fileName,folderName+'/'+files['audio'][0]['originalFilename'],(error)=>{
          console.log(error);
      });*/
       console.log(files);
   });
   res.send({'soccerTeam':'Arsenal'})
});

module.exports = router;