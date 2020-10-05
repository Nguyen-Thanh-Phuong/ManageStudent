package com.example.managestudent.controller.lop

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.Lop
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LopController {
    var list:MutableList<Lop> = mutableListOf()
    private lateinit var context:Context
    private var lopInterface:LopInterface?=null
    private constructor(){initLop()}
    companion object
    {
        val instance = LopController()
        fun getInstance(context: Context): LopController {
            instance.context = context
            return instance
        }
        fun getInstance(context: Context,lopInterface: LopInterface): LopController {
            getInstance(context).lopInterface = lopInterface
            return instance
        }
    }
    private fun initLop()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object :ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if(p0.exists())
                {
                    for(data in p0.children)
                    {
                        val value = data.getValue(Lop::class.java)
                        if(value!=null){
                            list.add(value)
                            Log.d("MAINN",value.TenLop)
                        }
                    }
                    lopInterface?.getListLop(list)
                }
            }

        })
    }
    fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("Lop")
    fun insert(lop: Lop)
    {
        if(list.find { it -> it.TenLop ==lop.TenLop }!=null)
        {
            Toast.makeText(context,"Lop ${lop.TenLop} Already",Toast.LENGTH_SHORT).show();
        }
        val firebase = getFirebaseInstance()
        val key = firebase.push().key
        if(key!=null)
        {
            lop.MaLop =key
            firebase.child(key).setValue(lop).addOnCompleteListener {
                Toast.makeText(context,"Insert Lop ${lop.TenLop} Success",Toast.LENGTH_SHORT).show();
            }
        }
    }
}