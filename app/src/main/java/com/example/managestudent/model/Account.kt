package com.example.managestudent.model

class Account(var key:String,var username:String,var password:String,var type:Int) {
    constructor(username:String,password:String):this("",username,password,0)
    constructor():this("","","",0)
}