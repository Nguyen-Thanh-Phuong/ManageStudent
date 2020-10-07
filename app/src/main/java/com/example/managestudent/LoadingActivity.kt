package com.example.managestudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.controller.buoihoc.BuoiHocInterface
import com.example.managestudent.controller.ctbh.CTBHController
import com.example.managestudent.controller.ctbh.CTBHInterface
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.day_of_week.DayOfWeekInterface
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.giaovien.GiaoVienInterface
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.khoa.KhoaInterface
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.lop.LopInterface
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.controller.monhoc.MonHocInterface
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.nhom.NhomInterface
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.controller.sinhvien.SinhVienInterface
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.CTBH
import com.example.managestudent.model.Khoa
import com.example.managestudent.model.Nhom
import com.google.firebase.database.FirebaseDatabase

class LoadingActivity : AppCompatActivity(),KhoaInterface, LopInterface, SinhVienInterface,
    MonHocInterface, GiaoVienInterface, NhomInterface, DayOfWeekInterface, BuoiHocInterface,

    CTBHInterface {
    var index:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

    }

    override fun getListKhoa(list: MutableList<Khoa>) {
        Log.d("MAINN","Khoa ${++index}")
    }

    override fun getListLop(list: MutableList<String>) {
        Log.d("MAINN","Lop ${++index}")

    }

    override fun getListSV(list: MutableList<String>) {
        Log.d("MAINN","SV ${++index}")

    }

    override fun getListMonHoc(list: MutableList<String>) {
        Log.d("MAINN","MH ${++index}")
    }

    override fun getPosition(index: Int) {
    }

    override fun getListGiaoVien(list: MutableList<String>) {
        Log.d("MAINN","GV ${++index}")
    }

    override fun getListNhom(list: MutableList<Nhom>) {
        Log.d("MAINN","Nhom ${++index}")
    }

    override fun getListDayOfWeek(list: MutableList<String>) {
        Log.d("MAINN","Day ${++index}")
    }

    override fun getListBuoiHoc(list: MutableList<BuoiHoc>) {
        Log.d("MAINN","BH ${++index}")
    }

    override fun getListCTBH(list: MutableList<CTBH>) {
        Log.d("MAINN","CTBH ${++index}")
    }
}