package com.example.managestudent

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.controller.ctbh.CTBHController
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.menu.MenuActivityGV
import com.google.firebase.database.FirebaseDatabase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        KhoaController.getInstance(this)
        BuoiHocController.getInstance(this)
        LopController.getInstance(this)
        SinhVienController.getInstance(this)
        MonHocController.getInstance(this)
        GiaoVienController.getInstance(this)
        NhomController.getInstance(this)
        DayOfWeekController.getInstance(this)
        CTBHController.getInstance(this)
        Handler(Looper.myLooper()!!).postDelayed({
            val intent = Intent(this@SplashScreen,
                MenuActivityGV::class.java)
            startActivity(intent)
            finish()
        },4000)
    }

}