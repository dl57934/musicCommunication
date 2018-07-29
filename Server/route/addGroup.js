let express = require('express');
const router = express.Router();

router.post('/', (req, res)=>{
    console.log('get Data', req.body);
});



module.exports = router;