package com.example.managestudent.model

class DayOfWeek(var MaDay:String,var TenDay:String) {
    constructor(TenDay: String):this("",TenDay)
    constructor():this("","")
}