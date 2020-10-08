package com.example.managestudent.model

class SinhVien(var maSV:String,var tenSV:String,var namsinh:String,var maLop:String,var username:String,var password:String,var img:String="") {
    constructor():this("","","","","","")
    constructor(tenSV: String,namsinh: String,maLop: String,username: String,password: String):this("",tenSV,namsinh,maLop,username,password)
}