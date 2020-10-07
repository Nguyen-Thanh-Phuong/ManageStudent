package com.example.managestudent.controller.buoihoc

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.BuoiHoc
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BuoiHocController {
    var list:MutableList<BuoiHoc> = mutableListOf()
    var listNhomCode:MutableList<String> = mutableListOf()
    var listDayCode:MutableList<String> = mutableListOf()
    private lateinit var context: Context
    private var BuoiHocInterface: BuoiHocInterface?=null
    private constructor(){initBuoiHoc()}
    companion object
    {
        val instances =
            BuoiHocController()
        fun getInstance(context: Context): BuoiHocController {
            instances.context = context
            return instances
        }
        fun getInstance(context: Context, buoiHocInterface: BuoiHocInterface): BuoiHocController {
            getInstance(
                context
            ).BuoiHocInterface = buoiHocInterface
            return instances
        }
    }
    private fun initBuoiHoc()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                listNhomCode.clear()
                listDayCode.clear()
                list.clear()
                if(p0.exists())
                {
                    for(data in p0.children)
                    {
                        val value = data.getValue(BuoiHoc::class.java)
                        if(value!=null)
                        {
                            listDayCode.add(value.maDay)
                            listNhomCode.add(value.maNhom)
                            list.add(value)
                        }
                    }
                    BuoiHocInterface?.getListBuoiHoc(list)
                }
            }
        })
    }
    fun getPos(maNhom:String,maday:String): Int {
        return list.indexOf(list.find { it.maNhom== maNhom&& it.maDay == maday})
    }
    fun getBuoiHoc(index:Int): BuoiHoc {
        return list[index]
    }
    fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("BuoiHoc")

    fun insert(BuoiHoc: BuoiHoc)
    {
        val firebase = getFirebaseInstance()
        val key = firebase.push().key
        if(key!=null)
        {
            firebase.child(key).setValue(BuoiHoc).addOnCompleteListener {
                Toast.makeText(context,"Insert BuoiHoc Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}