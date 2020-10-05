package com.example.managestudent.controller.khoa

import android.content.Context
import android.widget.Toast
import com.example.managestudent.model.Khoa
import com.google.firebase.database.*
import java.lang.Exception

class KhoaController {
    var context: Context? =null
    var khoaInterface: KhoaInterface?=null
    val khoaList = mutableListOf<Khoa>()
    private constructor(){}
    companion object
    {
        var instances: KhoaController =
            KhoaController()
        fun getInstance(context: Context): KhoaController {
            if(instances ==null)
                instances =
                    KhoaController()
            instances.context =context
            return instances
        }
        fun getInstance(context: Context,khoaInterface: KhoaInterface): KhoaController {
            if(instances ==null)
                instances =
                    KhoaController()
            instances.context =context
            instances.khoaInterface = khoaInterface
            return instances
        }
    }


    private fun getFirebase(): DatabaseReference {
        return FirebaseDatabase.getInstance().getReference("Khoa")
    }
    fun getAllKhoa()
    {
        var res = getFirebase()
        var valueEventListener = object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                khoaList.clear()
                if(p0.exists())
                {
                    for (h in p0.children)
                    {
                        val khoa = h.getValue(Khoa::class.java)
                        if (khoa != null) {

                            khoaList.add(khoa)
                        }
                    }
                    khoaInterface?.getListKhoa(khoaList)
                }
            }
        }
        res.addValueEventListener(valueEventListener)
    }
    fun getKhoa(map:Map<String,String>)
    {
        val database = getFirebase()
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