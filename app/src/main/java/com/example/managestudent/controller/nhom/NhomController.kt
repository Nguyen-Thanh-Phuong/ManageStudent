package com.example.managestudent.controller.nhom

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.controller.custom_spinner.CustomSpinnerItem
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.model.Nhom
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NhomController {
    var list:MutableList<Nhom> = mutableListOf()
    var listNhomCode:MutableList<String> = mutableListOf()
    var listNhomName:MutableList<String> = mutableListOf()
    private lateinit var context: Context
    private var nhomInterface: NhomInterface?=null
    private constructor(){initNhom()}
    companion object
    {
        val instances =
            NhomController()
        fun getInstance(context: Context): NhomController {
            instances.context = context
            return instances
        }
        fun getInstance(context: Context, nhomInterface: NhomInterface): NhomController {
            getInstance(
                context
            ).nhomInterface = nhomInterface
            return instances
        }
    }
    private fun getBuoiHoc(maSV:String,maNhom:String)
    {

    }

    private fun initNhom()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                listNhomCode.clear()
                listNhomName.clear()
                list.clear()
                if(p0.exists())
                {
                    for(data in p0.children)
                    {

                        val value = data.getValue(Nhom::class.java)
                        if(value!=null)
                        {
                            listNhomCode.add(value.MaNhom)
                            listNhomName.add(value.TenNhom)
                            list.add(value)

                        }
                    }
                    nhomInterface?.getListNhom(list)
                }
            }
        })
    }
    fun getListName(): MutableList<String> {
        val instace = GiaoVienController.instances
        val instance = MonHocController.instances
        val listResult = mutableListOf<String>()
        list.forEach {
            val MHName =instance.getTenMH(it.MaMH)
            val GVName = instace.getGVName(it.MaGV)
            listResult.add("${it.TenNhom} - $MHName - $GVName")
        }
        return listResult
    }
    fun getNhomToString(nhomCode:String): String {
        val instace = GiaoVienController.instances
        val instance = MonHocController.instances
        var nhomToString =""
        list[listNhomCode.indexOf(nhomCode)].let {
            val MHName =instance.getTenMH(it.MaMH)
            val GVName = instace.getGVName(it.MaGV)
            nhomToString = "${it.TenNhom} - $MHName - $GVName"
        }
        return nhomToString
    }
    fun getPos(MaNhom:String): Int {
        return listNhomCode.indexOf(MaNhom)
    }
    fun getNhomCode(index:Int): String {
        return listNhomCode[index]
    }
    fun getNhom(index:Int): Nhom {
        return list[index]
    }
    fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("Nhom")

    fun insert(nhom: Nhom)
    {
        if(list.find { it -> it.TenNhom ==nhom.TenNhom }!=null)
        {
            Toast.makeText(context,"Nhom ${nhom.TenNhom} Already", Toast.LENGTH_SHORT).show();
        }
        val firebase = getFirebaseInstance()
        val key = firebase.push().key
        if(key!=null)
        {
            nhom.MaNhom =key
            firebase.child(key).setValue(nhom).addOnCompleteListener {
                Toast.makeText(context,"Insert Nhom ${nhom.TenNhom} Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}