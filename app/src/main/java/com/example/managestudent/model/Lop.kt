package com.example.managestudent.model

import com.google.firebase.database.*

class Lop(var MaLop:String,var TenLop:String,var MaKhoa:String) {
    constructor() : this("","","")
    constructor(TenLop:String,MaKhoa:String):this("",TenLop,MaKhoa)
}