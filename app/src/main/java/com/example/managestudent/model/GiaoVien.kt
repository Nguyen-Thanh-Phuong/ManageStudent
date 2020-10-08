package com.example.managestudent.model

class GiaoVien(var MaGV:String, var TenGV:String) {
    constructor(TenGV: String):this("",TenGV)
    constructor():this("","")
}