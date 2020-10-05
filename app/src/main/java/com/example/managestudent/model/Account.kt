package com.example.managestudent.model

class Account(var key:String,var username:String,var password:String,var type:Int) {
    constructor(username:String,password:String,type: Int):this("",username,password,type)
    constructor():this("","","",0)

}