const express = require('express');
const router = express.Router();
const fs = require('fs');
const authMusic = require('../model/authMusic');
const createModel = require('../model/connectMongoDB');
let DBInfo = new createModel();
let model = DBInfo.createSchema();

router.post('/',(req, res)=>{
   let multiparty = require('multiparty');
   let form = new multiparty.Form({
       autoFiles:false,
       uploadDir: 'music',
   });

   form.parse(req, (err, fields, files)=>{
      console.log(err);
      let folderName = files['audio'][0]['path'].substr(0,5);
      let fileName =  files['audio'][0]['path'].substr(6);

      fs.rename(folderName+'/'+fileName,folderName+'/'+files['audio'][0]['originalFilename'],(error)=>{
          console.log(error);
      });
       let gsf = DBInfo.createGridFs();
       authMusic(files, gsf, model);
   });
   res.send({'soccerTeam':'Arsenal'})
});

module.exports = router;