const http = require('http');
let express = require('express');
const logger = require('morgan');
const volleyRouter = require('./route/getVolleyData');
const createModel = require('./model/connectMongoDB');
const bodyParser = require('body-parser');

app = express();

app.use(logger('dev'));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb',extended: true}));
app.use('/upload', volleyRouter);
app.set('port', 3000);



http.createServer(app).listen(app.get('port'),()=>{
   console.log('포트 3000에 연결');
});