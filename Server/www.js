http = require('http');
express = require('express');

app = express();
app.set('port', 3000);


http.createServer(app).listen(app.get('port'),()=>{
   console.log('포트 3000에 연결')
});