package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.giaovien.GiaoVienInterface
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.controller.monhoc.MonHocInterface
import com.example.managestudent.controller.nhom.NhomAdapter
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.nhom.NhomInterface
import com.example.managestudent.model.GiaoVien
import com.example.managestudent.model.Nhom
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

class NhomActivity : AppCompatActivity(), MonHocInterface, GiaoVienInterface, NhomInterface {
    lateinit var edtGroupNameLayout:TextInputLayout
    lateinit var edtGroupName : TextInputEditText
    lateinit var spinnerMH:MaterialSpinner
    lateinit var spinnerGV:MaterialSpinner
    lateinit var recyclerView: RecyclerView

    val instanceNhom = NhomController.getInstance(this,this)
    val instancesMH = MonHocController.getInstance(this,this)
    val instancesGV = GiaoVienController.getInstance(this,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nhom)
        //init hint layout
        edtGroupNameLayout = findViewById(R.id.edt_layout_input)
        edtGroupNameLayout.hint = "Nhap Ten Nhom"

        //init editText
        edtGroupName = findViewById(R.id.edt_input)
        //recycler View init
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager =LinearLayoutManager(this)
        //init spinner
        spinnerMH = findViewById<LinearLayout>(R.id.spnMH).findViewById(R.id.customSpinner)
        spinnerGV = findViewById<LinearLayout>(R.id.spnGV).findViewById(R.id.customSpinner)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
    }

    override fun onResume() {
        super.onResume()

        spinnerMH.setItems(instancesMH.listMonHocName)
        spinnerGV.setItems(instancesGV.listGiaoVienName)
        setAdapter(instanceNhom.list)
    }
    private fun insert()
    {
        val groupName = edtGroupName.text.toString()
        val maMH = instancesMH.getMaMonHoc(spinnerMH.selectedIndex)
        val maGV = instancesGV.getMaGV(spinnerGV.selectedIndex)
        var nhom = Nhom(groupName,maMH,maGV)
        instanceNhom.insert(nhom)
    }

    override fun getListMonHoc(list: MutableList<String>) {
        spinnerMH.setItems(list)
    }

    override fun getPosition(index: Int) {
    }

    override fun getListGiaoVien(list: MutableList<String>) {
        spinnerGV.setItems(list)
    }

    override fun getListNhom(list: MutableList<Nhom>) {
        setAdapter(list)
    }
    private fun setAdapter(list: MutableList<Nhom>)
    {
        val adapter = NhomAdapter(this,list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun getCodeSelect(NhomCode: String) {
    }
}