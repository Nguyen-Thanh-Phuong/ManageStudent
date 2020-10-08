package com.example.managestudent.model

class Nhom(var MaNhom:String, var TenNhom:String, var MaMH:String, var MaGV:String) {
    constructor(TenNhom: String,MaMH: String,MaGV: String):this("",TenNhom,MaMH,MaGV)
    constructor():this("","","","")
}