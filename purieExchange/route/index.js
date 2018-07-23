let express = require('express');
let router = express();

router.get('/' ,(req, res)=>{
    res.render('index');
})

router.post('/', (req, res)=>{
    console.log('호출 완료');
})

module.exports = router;
