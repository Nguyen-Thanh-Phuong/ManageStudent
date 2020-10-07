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
    var listBHCode:MutableList<String> = mutableListOf()
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
        Log.d("MAIN","Hihi" +instanceFirebase.toString())

        instanceFirebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                listBHCode.clear()
                listSVCode.clear()
                list.clear()
                if(p0.exists())
                {

                    for(data in p0.children)
                    {
                        val value = data.getValue(CTBH::class.java)
                        if(value!=null)
                        {
                            listBHCode.add(value.maBH)
                            listSVCode.add(value.maSV)
                            list.add(value)
                        }
                    }
                    cTBHInterface?.getListCTBH(list)
                }
            }
        })
    }
    fun getPos(MaCTBH:String): Int {
        return listBHCode.indexOf(MaCTBH)
    }
    fun getCTBH(index:Int): CTBH {
        return list[index]
    }

    fun insert(CTBH: CTBH)
    {
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