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
import com.jaredrummler.materialspinner.MaterialSpinner

class GiangVienActivity : AppCompatActivity(),GiaoVienInterface {
    lateinit var instances:GiaoVienController;
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giang_vien)
        instances = GiaoVienController.getInstance(this,this)
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
        setAdapter(instances.listGiaoVienName)
    }

    fun insert()
    {
        val textView = findViewById<TextInputEditText>(R.id.edt_input)
        val nameGV = textView.text.toString()
        var GV = GiaoVien(nameGV)
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