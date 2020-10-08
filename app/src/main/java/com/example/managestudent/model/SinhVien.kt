package com.example.managestudent.model

class SinhVien(var maSV:String,var tenSV:String,var namsinh:String,var maLop:String) {
    constructor():this("","","","")
    constructor(tenSV: String,namsinh: String,maLop: String):this("",tenSV,namsinh,maLop)
}