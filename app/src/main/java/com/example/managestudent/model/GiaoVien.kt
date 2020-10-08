package com.example.managestudent.model

class GiaoVien(var MaGV:String, var TenGV:String,var username:String,var password:String,var img:String="") {
    constructor(TenGV: String,username: String,password: String):this("",TenGV,username,password)
    constructor():this("","","","")
}