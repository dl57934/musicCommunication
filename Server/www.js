
const logger = require('morgan');
let express = require('express');
let app = express();
const http = require('http');
const volleyRouter = require('./route/getVolleyData');
const bodyParser = require('body-parser');
app = express();
app.use(logger('dev'));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb',extended: true}));
app.use('/test', volleyRouter);
app.set('port', 3000);


http.createServer(app).listen(app.get('port'),()=>{
   console.log('포트 3000에 연결')
});