let express = require('express');
let router = express();

router.get('/' ,(req, res)=>{
    res.render('index', {musicName:'_Digimon OST_ Butterfly - Fingerstyle Guitar Cover.mp3'});
})

router.post('/', (req, res)=>{
    console.log('호출 완료');
})

module.exports = router;
