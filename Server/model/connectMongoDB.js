const mongoose = require('mongoose');

let database;
let userSchema;
let UserModel;

connectDB = ()=>{
  const databaseUrl = 'mongodb://localhost:27017/local';

  console.log('데이터 베이스 연결을 시도합니다.');
  database = mongoose.connection;
  mongoose.connect(databaseUrl);
  database.on('error', console.error.bind(console, 'mongoose connection error.'));
  database.on('open', ()=>{
     console.log("데이터베이스에 연결되었습니다.: "+databaseUrl);
  });
};

module.exports = connectDB;
