package com.example.managestudent.controller.khoa

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.Khoa
import com.google.firebase.database.*
import java.lang.Exception

class KhoaController {
    var context: Context? =null
    var khoaInterface: KhoaInterface?=null
    val khoaList = mutableListOf<Khoa>()
    private var khoaCode = mutableListOf<String>()
    private var khoaName = mutableListOf<String>()
    var instanceFirebase = FirebaseDatabase.getInstance().getReference("Khoa")
    private constructor(){getAllKhoa()}
    companion object
    {
        var instances: KhoaController = KhoaController()
        fun getInstance(context: Context): KhoaController {
            instances.context =context
            return instances
        }
        fun getInstance(context: Context,khoaInterface: KhoaInterface): KhoaController {
            getInstance(context).khoaInterface = khoaInterface
            return instances
        }
    }


    fun getAllKhoa()
    {
        var res = instanceFirebase
        var valueEventListener = object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                khoaCode.clear()
                khoaName.clear()
                khoaList.clear()
                if(p0.exists())
                {
                    for (h in p0.children)
                    {
                        val khoa = h.getValue(Khoa::class.java)
                        if (khoa != null)
                        {
                            khoaList.add(khoa)
                            khoaCode.add(khoa.maKhoa)
                            khoaName.add(khoa.tenKhoa)
                        }
                    }
                    khoaInterface?.getListKhoa(khoaList)
                }

            }
        }
        res.addValueEventListener(valueEventListener)
    }

    fun getPos(maKhoa: String):Int
    {
        return try {
            khoaCode.indexOf(maKhoa)
        }catch (e:Exception)
        {
            return 0;
        }
    }
    fun getKhoa(map:Map<String,String>)
    {
        val database = instanceFirebase
        var query: Query? =null
        for(data in map)
        {
            query = database.orderByChild(data.key).equalTo(data.value)
        }

        query?.addListenerForSingleValueEvent(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                khoaList.clear()
                if(p0.exists())
                {
                    for (data in p0.children)
                    {
                        try {
                            khoaList.add(data.getValue(Khoa::class.java)!!)
                        }catch (e: Exception){break;}
                    }
                }
            }
        })
    }
    fun getKhoaByCode(maKhoa:String): Khoa? {
        try {
            return khoaList.find { khoa -> khoa.maKhoa==maKhoa }
        }catch (e:Exception)
        {
            return null
        }
    }
    fun getKhoaByName(tenKhoa:String): Khoa? {
        try {
            return khoaList.find { khoa -> khoa.tenKhoa==tenKhoa }
        }catch (e:Exception)
        {
            return null
        }
    }
    fun getKhoaName(maKhoa: String): String {
        return khoaName[khoaCode.indexOf(maKhoa)]
    }
    fun getKhoaByIndex(index:Int):Khoa?
    {
        try {
            return khoaList[index]
        }catch (e:Exception)
        {
            return null
        }
    }
    fun insert(khoa: Khoa)
    {
        if(khoaList.find { x -> x.tenKhoa==khoa.tenKhoa }!=null)
        {
            Toast.makeText(context,"Lỗi : Đã Có Khoa Này",Toast.LENGTH_SHORT).show()
            return
        }
        val db = FirebaseDatabase.getInstance().getReference("Khoa")
        val id = db.push().key
        if(id!=null)
        {
            khoa.maKhoa =id
            db.child(id).setValue(khoa).addOnCompleteListener {
                Toast.makeText(context,"Insert Khoa Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}