package com.example.managestudent.controller.nhom

import com.example.managestudent.model.Nhom

interface NhomInterface {
    fun getListNhom(list: MutableList<Nhom>)
    fun getCodeSelect(NhomCode:String)
}
