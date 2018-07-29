const http = require('http');
let express = require('express');
const logger = require('morgan');
const volleyRouter = require('./route/getVolleyData');
const relativeRouter = require('./route/addUser');
const addGroup = require('./route/addGroup');
const createModel = require('./model/connectMongoDB');
const bodyParser = require('body-parser');
let DbInfo = new createModel();
app = express();

app.use(logger('dev'));
app.use(bodyParser.json({limit: '50mb'}));
app.use(bodyParser.urlencoded({limit: '50mb',extended: true}));
app.use('/upload', volleyRouter);
app.use('/addMember',relativeRouter);
app.use('/addGroup', addGroup);
app.set('port', 3000);



http.createServer(app).listen(app.get('port'),()=>{
   console.log('포트 3000에 연결');
   DbInfo.connectDB();
});