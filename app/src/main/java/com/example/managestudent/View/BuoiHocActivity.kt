package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.buoihoc.BuoiHocAdapter
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.controller.buoihoc.BuoiHocInterface
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.nhom.NhomAdapter
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.nhom.NhomInterface
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.Nhom
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

class BuoiHocActivity : AppCompatActivity(), BuoiHocInterface {
    lateinit var edtStart: TextInputEditText
    lateinit var edtEnd: TextInputEditText
    lateinit var spinnerNhom: MaterialSpinner
    lateinit var spinnerDay: MaterialSpinner
    lateinit var recyclerView: RecyclerView
    private val instanceNhom =NhomController.instances
    private val instanceDay = DayOfWeekController.instances
    private val instance = BuoiHocController.getInstance(this,this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buoi_hoc)
        //init hint layout
        findViewById<LinearLayout>(R.id.edt_Start).findViewById<TextInputLayout>(R.id.edt_layout_input).hint = "Tiet Start"
        edtStart = findViewById<LinearLayout>(R.id.edt_Start).findViewById(R.id.edt_inputNumber)

        findViewById<LinearLayout>(R.id.edt_End).findViewById<TextInputLayout>(R.id.edt_layout_input).hint = "Tiet End"
        edtEnd = findViewById<LinearLayout>(R.id.edt_End).findViewById(R.id.edt_inputNumber)

        //recycler View init
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = BuoiHocAdapter(this,instance.list)
        recyclerView.adapter = adapter
        //init spinner
        spinnerNhom = findViewById<LinearLayout>(R.id.spnNhom_BH).findViewById(R.id.customSpinner)
        spinnerDay = findViewById<LinearLayout>(R.id.spnDay_BH).findViewById(R.id.customSpinner)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
        spinnerNhom.setItems(instanceNhom.getListName())
        spinnerDay.setItems(instanceDay.getListDay())
    }
    private fun insert()
    {
        val startTime = edtStart.text.toString()
        val endTime = edtEnd.text.toString()
        val maNhom = instanceNhom.getNhomCode(spinnerNhom.selectedIndex)
        val maDay = instanceDay.getDayCode(spinnerDay.selectedIndex)
        var buoiHoc = BuoiHoc(maNhom,startTime,endTime,maDay)
        if(!buoiHoc.checkData()){
            Toast.makeText(this,"Invalid Data",Toast.LENGTH_SHORT).show()
            return
        }
        instance.insert(buoiHoc)
    }


    override fun getListBuoiHoc(list: MutableList<BuoiHoc>) {
        val adapter = BuoiHocAdapter(this,instance.list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}