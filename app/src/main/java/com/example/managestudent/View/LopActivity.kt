package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.lop.LopAdapter
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.lop.LopInterface
import com.example.managestudent.controller.nhom.NhomAdapter
import com.example.managestudent.model.Khoa
import com.example.managestudent.model.Lop
import com.example.managestudent.model.Nhom
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

class LopActivity : AppCompatActivity(), LopInterface {
    lateinit var edtGroupNameLayout: TextInputLayout
    lateinit var edtGroupName : TextInputEditText

    lateinit var spinnerKhoa: MaterialSpinner
    lateinit var recyclerView: RecyclerView

    val instanceLop = LopController.getInstance(this,this)
    val instanceKhoa = KhoaController.instances
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lop)

        //init hint layout
        edtGroupNameLayout = findViewById(R.id.edt_layout_input)
        edtGroupNameLayout.hint = "Nhap Ten Lop"

        //init editText
        edtGroupName = findViewById(R.id.edt_input)
        //recycler View init
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //init spinner
        spinnerKhoa = findViewById<LinearLayout>(R.id.spnKhoa_Lop).findViewById(R.id.customSpinner)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
    }
    private fun insert()
    {
        val groupName = edtGroupName.text.toString()
        val maKhoa = instanceKhoa.getKhoaCodeByIndex(spinnerKhoa.selectedIndex)
        var lop = Lop(groupName,maKhoa)
        instanceLop.insert(lop)
    }
    private fun setAdapter(list: MutableList<Lop>)
    {
        val adapter = LopAdapter(this,list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    override fun onResume() {
        super.onResume()

        spinnerKhoa.setItems(instanceKhoa.khoaName)
        setAdapter(instanceLop.list)
    }

    override fun getListLop(list: MutableList<Lop>) {
        setAdapter(list)
    }
}