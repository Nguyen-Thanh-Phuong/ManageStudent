package com.example.managestudent.model

class SinhVien(var maSV:String,var tenSV:String,var namsinh:Int,var maLop:String) {
    constructor():this("","",0,"")
    constructor(tenSV: String,namsinh: Int,maLop: String):this("",tenSV,namsinh,maLop)
}