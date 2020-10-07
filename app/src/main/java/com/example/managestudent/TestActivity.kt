package com.example.managestudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.khoa.KhoaInterface
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.lop.LopInterface
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.controller.sinhvien.SinhVienInterface
import com.example.managestudent.model.Khoa
import com.example.managestudent.model.SinhVien
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(){

    lateinit var editName:EditText
    lateinit var btnSave:Button
    lateinit var res:DatabaseReference
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        LopController.getInstance(this,this)
//        KhoaController.getInstance(this,this).getAllKhoa()
//        KhoaController.getInstance(this,this).getList()
        btnSave = findViewById(R.id.btnSubmit)
        spinner = findViewById(R.id.spinnerKhoa)
        //Event when clicked mouse in Activity
        btnSave.setOnClickListener {
            //saveData()
        }
//        spinnerSV.onItemSelectedListener = object :
//            AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // write code to perform some action
//            }
//
//            override fun onItemSelected(
//                p0: AdapterView<*>?,
//                p1: android.view.View?,
//                p2: Int,
//                p3: Long
//            ) {
//                val LopCode = SinhVienController.getInstance(this).getLopCode(p2)
//                var lopIndex = LopController.instance.getPos(LopCode)
//                var khoaIndex = LopController.instance.getLop()
//            }
//        }
    }


//    private fun saveData() {
//        val name = editName.text.toString()
//        if(name.isEmpty())
//        {
//            editName.error = "Please Input The Name"
//            return
//        }
//        val sinhVien = LopController.instance.findLopCodeByIndex(spinnerLop.selectedItemPosition)?.let {
//            SinhVien(name,edtYear.text.toString(),
//                it
//            )
//        }
//        val instance = SinhVienController.getInstance(this,this)
//        if (sinhVien != null) {
//            instance.insert(sinhVien)
//        }
//    }
//
//    override fun getListKhoa(list: MutableList<Khoa>) {
//        val listData = mutableListOf<String>()
//        list.forEach { khoa -> listData.add(khoa.tenKhoa)}
//        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listData)
//        spinner.adapter = adapter
//    }
//
//    override fun getListLop(list: MutableList<String>) {
//        spinnerLop.adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list)
//    }
//
//    override fun getListSV(list: MutableList<SinhVien>) {
//        TODO("Not yet implemented")
//    }

}