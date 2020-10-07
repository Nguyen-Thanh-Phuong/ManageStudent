package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.controller.buoihoc.BuoiHocInterface
import com.example.managestudent.controller.custom_spinner.CustomSpinnerAdapter
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.nhom.NhomInterface
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.controller.sinhvien.SinhVienInterface
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.Nhom
import com.example.managestudent.model.SinhVien
import com.google.android.material.textfield.TextInputEditText
import com.jaredrummler.materialspinner.MaterialSpinner
import kotlinx.android.synthetic.main.activity_ctbh.*

class CTBHActivity : AppCompatActivity(),SinhVienInterface,NhomInterface {
    lateinit var spnMaSV:MaterialSpinner
    lateinit var spnMaNhom:MaterialSpinner
    //val instanceLop = LopController.getInstance(this)
    private val instanceNhom =NhomController.instances
    private val instanceSV = SinhVienController.instance
    private val instance = BuoiHocController.instances

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ctbh)

        spnMaSV = findViewById<LinearLayout>(R.id.spnMaSV_CTBH).findViewById(R.id.customSpinner)
        spnMaNhom = findViewById<LinearLayout>(R.id.spnMaNhom_CTBH).findViewById(R.id.customSpinner)
    }

    override fun getListSV(list: MutableList<String>) {
        //spnMaSV.setItems(instancesSV.getListSVToString())
    }

    override fun getListNhom(list: MutableList<Nhom>) {
        //spnMaNhom.setItems(instancesNhom.getListName())
    }

}