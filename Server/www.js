
const logger = require('morgan');
let express = require('express');
let app = express();
const http = require('http');
const volleyRouter = require('./route/getVolleyData');

app = express();
app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use('/test', volleyRouter);
app.set('port', 3000);


http.createServer(app).listen(app.get('port'),()=>{
   console.log('포트 3000에 연결')
});