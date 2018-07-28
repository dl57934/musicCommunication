savingUser = (model, data)=>{
let user = new model(data);
user.save((err)=>{
    if(err){
        console.log('error 발생');
    }
    console.log('사용자 데이터 추가');
})
}



module.exports = savingUser;