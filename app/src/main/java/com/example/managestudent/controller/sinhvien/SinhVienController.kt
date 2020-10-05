package com.example.managestudent.controller.sinhvien

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.managestudent.model.SinhVien
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class SinhVienController {
    var list = mutableListOf<SinhVien>()
    lateinit var context:Context
    var sinhVienInterface:SinhVienInterface?=null
    private constructor(){getAllSinhVien()}
    companion object
    {
        private val instance = SinhVienController();
        fun getInstance(context: Context): SinhVienController {
            instance.context = context
            return instance
        }
        fun getInstance(context: Context,sinhVienInterface: SinhVienInterface)
        {
            getInstance(context).sinhVienInterface = sinhVienInterface
        }
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
                if(p0.exists())
                {
                    for (data in p0.children)
                    {
                        val value = data.getValue(SinhVien::class.java)
                        if(value!=null)
                        {
                            list.add(value)
                            Log.d("MAINN",value.tenSV)
                        }
                    }
                }
                sinhVienInterface?.getListSV(list)
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