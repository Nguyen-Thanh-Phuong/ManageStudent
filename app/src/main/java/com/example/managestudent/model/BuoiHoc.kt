package com.example.managestudent.model

import com.example.managestudent.controller.buoihoc.BuoiHocController
import java.lang.Exception

class BuoiHoc(var maNhom:String,var tietStart:String,var tietEnd:String,var maDay:String) {
    constructor():this("","","","")
    fun checkData(): Boolean {
        return try {
            tietStart.toInt()<tietEnd.toInt() && tietEnd.toInt()-tietStart.toInt()<=4
        }catch (e:Exception)
        {
            false
        }
    }

}