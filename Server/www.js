http = require('http');
express = require('express');

app = express();



http.createServer(app).listen(3000,()=>{
   console.log('포트 3000에 연결')
});