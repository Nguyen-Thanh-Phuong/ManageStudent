package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.lop.LopAdapter
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.sinhvien.SinhVienAdapter
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.controller.sinhvien.SinhVienInterface
import com.example.managestudent.model.Lop
import com.example.managestudent.model.SinhVien
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

class SinhVienActivity : AppCompatActivity(), SinhVienInterface {
    lateinit var edtNameSVLayout: TextInputLayout
    lateinit var edtNameSV: TextInputEditText

    lateinit var edtNamSinhSVLayout: TextInputLayout
    lateinit var edtNamSinhSV: TextInputEditText

    lateinit var edtUserSVLayout: TextInputLayout
    lateinit var edtUserSV: TextInputEditText

    lateinit var edtPassSVLayout: TextInputLayout
    lateinit var edtPassSV: TextInputEditText

    lateinit var spinnerLop: MaterialSpinner
    lateinit var recyclerView: RecyclerView

    val instanceSV = SinhVienController.getInstance(this, this)
    val instanceLop = LopController.instance
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sinh_vien)

        //init hint layout
        edtNameSVLayout =
            findViewById<LinearLayout>(R.id.txvNameSV_SV).findViewById(R.id.edt_layout_input)
        edtNameSVLayout.hint = "Nhap Tên SV"

        edtNameSV = findViewById<LinearLayout>(R.id.txvNameSV_SV).findViewById(R.id.edt_input)
        //NamSinh
        edtNamSinhSVLayout =
            findViewById<LinearLayout>(R.id.txvNamSinh_SV).findViewById(R.id.edt_layout_input)
        edtNamSinhSVLayout.hint = "Nhap Năm Sinh"
        edtNamSinhSV =
            findViewById<LinearLayout>(R.id.txvNamSinh_SV).findViewById(R.id.edt_input)
        //User
        edtUserSVLayout =
            findViewById<LinearLayout>(R.id.txvUser_SV).findViewById(com.example.managestudent.R.id.edt_layout_input)
        edtUserSVLayout.hint = "Username"

        edtUserSV =findViewById<LinearLayout>(R.id.txvUser_SV).findViewById(com.example.managestudent.R.id.edt_input)
        //Password
        edtPassSVLayout =
            findViewById<LinearLayout>(R.id.txvPass_SV).findViewById(com.example.managestudent.R.id.edt_layout_input)
        edtPassSVLayout.hint = "Password"
        edtPassSV =
            findViewById<LinearLayout>(R.id.txvPass_SV).findViewById(com.example.managestudent.R.id.edt_input)

        //recycler View init
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //init spinner
        spinnerLop = findViewById(R.id.customSpinner)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
    }

    private fun insert() {
        if (edtNameSV.text.toString().trim().equals(""))
            edtNameSV.error = "Field is not Empty"
        if (edtNamSinhSV.text.toString().trim().equals(""))
            edtNamSinhSV.error = "Field is not Empty"
        if (edtUserSV.text.toString().trim().equals(""))
            edtUserSV.error = "Field is not Empty"
        if (edtPassSV.text.toString().trim().equals(""))
            edtPassSV.error = "Field is not Empty"
        val groupName = edtNameSV.text.toString()
        val maLop = instanceLop.getLop(spinnerLop.selectedIndex).MaLop
        var sv = SinhVien(
            groupName, edtNamSinhSV.text.toString(), maLop, edtUserSV.text.toString(),
            edtPassSV.text.toString()
        )
        instanceSV.insert(sv)
    }

    private fun setAdapter(list: MutableList<SinhVien>) {
        val adapter = SinhVienAdapter(this, list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()

        spinnerLop.setItems(instanceLop.listLopName)
        setAdapter(instanceSV.list)
    }


    override fun getListSV(list: MutableList<SinhVien>) {
        setAdapter(list)
    }
}