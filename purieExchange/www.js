const http = require('http');
let express = require('express');
const logger = require('morgan');
const bodyParser = require('body-parser');
const index = require('./route/index');
const app = express();
const static = require('serve-static');
const path = require('path');

// app.get(app.router);


app.use(express.static('public'));
app.set('views', __dirname+'/public/views');
app.set('view engine', 'ejs');
app.use(logger('dev'));

app.use(bodyParser.urlencoded({extended: false}));
app.use(bodyParser.json());

app.use('/', index);


http.createServer(app).listen(8000, ()=>{
    console.log('8000에 연결되었다.');
    console.log(__dirname);
});


