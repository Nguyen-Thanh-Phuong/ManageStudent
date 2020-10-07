package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.monhoc.MonHocAdapter
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.controller.monhoc.MonHocInterface
import com.example.managestudent.model.MonHoc
import kotlinx.android.synthetic.main.activity_input_mon_hoc.*

class InputMonHoc : AppCompatActivity(), MonHocInterface {
    lateinit var recycle:RecyclerView
    var instances = MonHocController.getInstance(this,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_mon_hoc)

        recycle = findViewById(R.id.recycler)
        recycle.layoutManager = LinearLayoutManager(this)

        btn_Insert_MonHoc.setOnClickListener {
            implementInsert()
        }
    }

    override fun getListMonHoc(list: MutableList<String>) {

        recycle.adapter = MonHocAdapter(this,list)
        (recycle.adapter as MonHocAdapter).notifyDataSetChanged()
    }

    override fun getPosition(index: Int) {
    }

    fun implementInsert()
    {
        var monHocName = edt_input_MonHoc.text.toString()
        var monHoc = MonHoc(monHocName)
        instances.insert(monHoc)
        edt_input_MonHoc.text?.clear()
    }
}