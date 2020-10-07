package com.example.managestudent.controller.monhoc

import android.content.Context
import android.widget.Toast
import com.example.managestudent.model.MonHoc
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MonHocController {
    var list:MutableList<MonHoc> = mutableListOf()
    var listMonHocCode:MutableList<String> = mutableListOf()
    var listMonHocName:MutableList<String> = mutableListOf()
    private lateinit var context: Context
    private var monHocInterface: MonHocInterface?=null
    private constructor(){initMonHoc()}
    companion object
    {
        val instances = MonHocController()
        fun getInstance(context: Context): MonHocController {
            instances.context = context
            return instances
        }
        fun getInstance(context: Context, MonHocInterface: MonHocInterface): MonHocController {
            getInstance(context).monHocInterface = MonHocInterface
            return instances
        }
    }
    private fun initMonHoc()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                listMonHocCode.clear()
                listMonHocName.clear()
                list.clear()
                if(p0.exists())
                {
                    for(data in p0.children)
                    {
                        val value = data.getValue(MonHoc::class.java)
                        if(value!=null)
                        {
                            listMonHocCode.add(value.MaMH)
                            listMonHocName.add(value.TenMH)
                            list.add(value)
                        }
                    }
                    monHocInterface?.getListMonHoc(listMonHocName)
                }
            }
        })
    }
    fun getTenMH(MaMH:String): String {
        return listMonHocName[listMonHocCode.indexOf(MaMH)]
    }
    fun getPos(MaMH:String): Int {
        return listMonHocCode.indexOf(MaMH)
    }
    fun getMaMonHoc(index:Int): String {
        return listMonHocCode[index]
    }
    fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("MonHoc")

    fun insert(MonHoc: MonHoc)
    {
        if(list.find { it -> it.TenMH ==MonHoc.TenMH }!=null)
        {
            Toast.makeText(context,"MonHoc ${MonHoc.TenMH} Already", Toast.LENGTH_SHORT).show();
        }
        val firebase = getFirebaseInstance()
        val key = firebase.push().key
        if(key!=null)
        {
            MonHoc.MaMH =key
            firebase.child(key).setValue(MonHoc).addOnCompleteListener {
                Toast.makeText(context,"Insert MonHoc ${MonHoc.TenMH} Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}