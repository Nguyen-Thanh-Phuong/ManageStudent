package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.giaovien.GiaoVienAdapter
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.giaovien.GiaoVienInterface
import com.example.managestudent.model.GiaoVien
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

class GiangVienActivity : AppCompatActivity(),GiaoVienInterface {
    lateinit var instances:GiaoVienController;
    lateinit var recyclerView: RecyclerView

    lateinit var edtName:TextInputEditText
    lateinit var edtUser:TextInputEditText
    lateinit var edtPass:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giang_vien)
        instances = GiaoVienController.getInstance(this,this)
        recyclerView = findViewById(R.id.recycler)
        edtName = findViewById<LinearLayout>(R.id.txvGVName_GV).findViewById(R.id.edt_input)
        findViewById<LinearLayout>(R.id.txvGVUser_GV).findViewById<TextInputLayout>(R.id.edt_layout_input).hint = "Username"
        edtUser = findViewById<LinearLayout>(R.id.txvGVUser_GV).findViewById(R.id.edt_input)
        findViewById<LinearLayout>(R.id.txvGVPass_GV).findViewById<TextInputLayout>(R.id.edt_layout_input).hint = "Password"
        edtPass = findViewById<LinearLayout>(R.id.txvGVPass_GV).findViewById(R.id.edt_input)

        recyclerView.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
        setAdapter(instances.listGiaoVienName)
    }

    fun insert()
    {
        val nameGV = edtName.text.toString()
        val pass = edtPass.text.toString()
        val user = edtUser.text.toString()
        var GV = GiaoVien(nameGV,user,pass)
        instances.insert(GV)
    }
    override fun getListGiaoVien(list: MutableList<String>) {
        setAdapter(list)
    }
    private fun setAdapter(list: MutableList<String>)
    {
        val adapter = GiaoVienAdapter(this,list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}