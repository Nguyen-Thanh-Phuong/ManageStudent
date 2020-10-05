package com.example.managestudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.khoa.KhoaInterface
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.lop.LopInterface
import com.example.managestudent.model.Khoa
import com.example.managestudent.model.Lop
import com.google.firebase.database.*

class TestActivity : AppCompatActivity(),
    LopInterface,KhoaInterface {

    lateinit var editName:EditText
    lateinit var btnSave:Button
    lateinit var res:DatabaseReference
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        LopController.getInstance(this)
        KhoaController.getInstance(this,this).getAllKhoa()
//        KhoaController.getInstance(this,this).getList()
        editName = findViewById(R.id.edtName)
        btnSave = findViewById(R.id.btnSubmit)
        spinner = findViewById(R.id.spinnerKhoa)
        //Event when clicked mouse in Activity
        btnSave.setOnClickListener {
            saveData()
        }
    }


    private fun saveData() {
        val name = editName.text.toString()
        if(name.isEmpty())
        {
            editName.error = "Please Input The Name"
            return
        }
        val lop = Lop(name,KhoaController.instances.getKhoaByIndex(spinner.selectedItemPosition)!!.maKhoa)
        val instance = LopController.getInstance(this,this)
        instance.insert(lop)

    }

    override fun getListKhoa(list: MutableList<Khoa>) {
        val listData = mutableListOf<String>()
        list.forEach { khoa -> listData.add(khoa.tenKhoa)}
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listData)
        spinner.adapter = adapter
    }

    override fun getListLop(list: MutableList<Lop>) {
        TODO("Not yet implemented")
    }

}