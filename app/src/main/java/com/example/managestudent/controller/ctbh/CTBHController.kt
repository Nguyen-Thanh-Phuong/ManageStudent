package com.example.managestudent.controller.ctbh

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.CTBH
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CTBHController
{
    var list:MutableList<CTBH> = mutableListOf()
    var listMHCode:MutableList<String> = mutableListOf()
    var listSVCode:MutableList<String> = mutableListOf()
    var instanceFirebase = FirebaseDatabase.getInstance().getReference("CTBH")
    private lateinit var context: Context
    private var cTBHInterface: CTBHInterface?=null
    private constructor(){initCTBH()}
    companion object
    {
        val instances =
            CTBHController()
        fun getInstance(context: Context): CTBHController {
            instances.context = context
            return instances
        }
        fun getInstance(context: Context, cTBHInterface: CTBHInterface): CTBHController {
            getInstance(
                context
            ).cTBHInterface = cTBHInterface
            return instances
        }
    }
    private fun initCTBH()
    {
        instanceFirebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                listMHCode.clear()
                listSVCode.clear()
                list.clear()
                if(p0.exists())
                {

                    for(data in p0.children)
                    {
                        val value = data.getValue(CTBH::class.java)
                        if(value!=null)
                        {
                            listMHCode.add(value.maMH)
                            listSVCode.add(value.maSV)
                            list.add(value)
                        }
                    }
                    cTBHInterface?.getListCTBH(list)
                }
            }
        })
    }
    fun getListCBTHBySVCode(sVCode:String): List<CTBH> {
        return list.filter {
            it.maSV==sVCode
        }
    }
    fun getPos(MaCTBH:String): Int {
        return listMHCode.indexOf(MaCTBH)
    }
    fun getCTBH(index:Int): CTBH {
        return list[index]
    }

    fun insert(CTBH: CTBH)
    {
        if(list.find {
                it.maMH==CTBH.maMH&&it.maSV==CTBH.maSV
            }!=null)
        {
            Toast.makeText(context,"Error : Data is Already ",Toast.LENGTH_SHORT).show()
            return
        }
        val firebase = instanceFirebase
        val key = firebase.push().key
        if(key!=null)
        {
            firebase.child(key).setValue(CTBH).addOnCompleteListener {
                Toast.makeText(context,"Insert CTBH Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}