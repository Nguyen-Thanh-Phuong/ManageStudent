package com.example.managestudent.controller.sinhvien

import android.content.Context
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.model.SinhVien
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SinhVienController {
    var list = mutableListOf<SinhVien>()
    var listCode = mutableListOf<String>()
    var listName = mutableListOf<String>()
    lateinit var context:Context
    var sinhVienInterface:SinhVienInterface?=null
    private constructor(){getAllSinhVien()}
    companion object
    {
        val instance = SinhVienController();
        fun getInstance(context: Context): SinhVienController {
            instance.context = context
            return instance
        }
        fun getInstance(context: Context,sinhVienInterface: SinhVienInterface): SinhVienController {
            getInstance(context).sinhVienInterface = sinhVienInterface
            return instance
        }
    }
    fun getName(code:String) = listName.get(listCode.indexOf(code))
    fun getLopCode(index:Int): String {
        return list[index].maSV
    }
    fun getListSVToString(): MutableList<String> {
        val listResult = mutableListOf<String>()
        val instance = LopController.instance
        list.forEach {
            Log.d("MAINN",instance.getLopToString(it.maLop))
            val value =it.tenSV+" - "+instance.getLopToString(it.maLop)
            listResult.add(value)
        }
        return listResult
    }
    private fun getFirebasePath() = FirebaseDatabase.getInstance().getReference("SinhVien")
    //Init Data Sinh vien
    private fun getAllSinhVien()
    {
        val firebase = getFirebasePath()
        firebase.addValueEventListener(object :ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                listCode.clear()
                listName.clear()
                if(p0.exists())
                {
                    for (data in p0.children)
                    {
                        val value = data.getValue(SinhVien::class.java)
                        if(value!=null)
                        {
                            listCode.add(value.maSV)
                            listName.add(value.tenSV)
                            list.add(value)
                        }
                    }
                }
                sinhVienInterface?.getListSV(listName)
            }
        })
    }

    fun insert(sinhVien: SinhVien)
    {
        val firebase = getFirebasePath()
        val keyid = firebase.push().key
        if(keyid!=null)
        {
            sinhVien.maSV = keyid
            firebase.child(keyid).setValue(sinhVien).addOnCompleteListener {
                Toast.makeText(context,"Insert sinh vien ${sinhVien.tenSV} successfully",Toast.LENGTH_SHORT).show()
            }
        }
    }
}