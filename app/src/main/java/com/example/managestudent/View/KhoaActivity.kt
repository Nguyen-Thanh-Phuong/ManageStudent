package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.khoa.KhoaInterface
import com.example.managestudent.controller.monhoc.MonHocAdapter
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.model.Khoa
import com.example.managestudent.model.MonHoc
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_input_mon_hoc.*

class KhoaActivity : AppCompatActivity(), KhoaInterface {
    lateinit var recycle: RecyclerView
    var instances = KhoaController.getInstance(this, this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_khoa)

        recycle = findViewById(R.id.recycler)
        recycle.layoutManager = LinearLayoutManager(this)
        val button = findViewById<Button>(R.id.btn)
        button.text = "Insert Khoa"
        button.setOnClickListener {
            implementInsert()
        }
    }

    private fun setAdapter(list: MutableList<String>) {
        recycle.adapter = MonHocAdapter(this, list)
        (recycle.adapter as MonHocAdapter).notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        setAdapter(instances.khoaName)
    }

    fun implementInsert() {
        val edt = findViewById<TextInputEditText>(R.id.edt_input)
        var khoaName = edt.text.toString()
        var khoa = Khoa(khoaName)
        instances.insert(khoa)
        edt.text?.clear()
    }

    override fun getListKhoa(list: MutableList<String>) {
        setAdapter(instances.khoaName)
    }
}