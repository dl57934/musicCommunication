const express = require('express');
const router = express.Router();

router.get('/',(req, res)=>{
   var groupName = req.params.groupName;
   console.log(groupName);
});

module.exports = router;