savingGroup = (model, data)=>{
    let user = new model(data);
    user.save((err)=>{
        if(err){
            console.log(err);
            return;
        }
        console.log('그룹 추가 완료');
    })
    }
    
    
    
    module.exports = savingUser;