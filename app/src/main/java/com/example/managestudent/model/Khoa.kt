package com.example.managestudent.model

class Khoa(var maKhoa:String,val tenKhoa:String) {
    constructor(tenKhoa: String):this("",tenKhoa)
    constructor():this("","")
}