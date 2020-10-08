package com.example.managestudent.controller.giaovien

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.GiaoVien
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GiaoVienController {
    var list:MutableList<GiaoVien> = mutableListOf()
    var listGiaoVienCode:MutableList<String> = mutableListOf()
    var listGiaoVienName:MutableList<String> = mutableListOf()
    private lateinit var context: Context
    private var giaoVienInterface: GiaoVienInterface?=null
    private constructor(){initGiaoVien()}
    companion object
    {
        val instances = GiaoVienController()
        fun getInstance(context: Context): GiaoVienController {
            instances.context = context
            return instances
        }
        fun getInstance(context: Context, giaoVienInterface: GiaoVienInterface): GiaoVienController {
            getInstance(context).giaoVienInterface = giaoVienInterface
            return instances
        }
    }
    private fun initGiaoVien()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                listGiaoVienCode.clear()
                listGiaoVienName.clear()
                list.clear()
                if(p0.exists())
                {
                    for(data in p0.children)
                    {
                        val value = data.getValue(GiaoVien::class.java)
                        if(value!=null)
                        {
                            listGiaoVienCode.add(value.MaGV)
                            listGiaoVienName.add(value.TenGV)
                            list.add(value)
                        }
                    }
                    giaoVienInterface?.getListGiaoVien(listGiaoVienName)
                }
            }
        })
    }
    fun getGVName(MaGV: String): String {
        return listGiaoVienName[listGiaoVienCode.indexOf(MaGV)]
    }
    fun getPos(MaGV:String): Int {
        return listGiaoVienCode.indexOf(MaGV)
    }
    fun getMaGV(index:Int): String {
        return listGiaoVienCode[index]
    }
    fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("GiaoVien")

    fun insert(GiaoVien: GiaoVien)
    {
        if(list.find { it -> it.TenGV ==GiaoVien.TenGV }!=null)
        {
            Toast.makeText(context,"GiaoVien ${GiaoVien.TenGV} Already", Toast.LENGTH_SHORT).show();
        }
        val firebase = getFirebaseInstance()
        val key = firebase.push().key
        if(key!=null)
        {
            GiaoVien.MaGV =key
            firebase.child(key).setValue(GiaoVien).addOnCompleteListener {
                Toast.makeText(context,"Insert GiaoVien ${GiaoVien.TenGV} Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}