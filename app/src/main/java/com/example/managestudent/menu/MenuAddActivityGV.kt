package com.example.managestudent.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.managestudent.R
import com.example.managestudent.View.*
import kotlinx.android.synthetic.main.activity_menu_add.*

class MenuAddActivityGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_add)
        card_add_khoa.setOnClickListener {
            startActivity(Intent(this,KhoaActivity::class.java))
        }

        card_add_lop.setOnClickListener {
            startActivity(Intent(this,LopActivity::class.java))
        }

        card_add_sv.setOnClickListener {
            startActivity(Intent(this,SinhVienActivity::class.java))
        }
        card_add_monhoc.setOnClickListener {
            startActivity(Intent(this,InputMonHoc::class.java))
        }
        card_add_gv.setOnClickListener {
            startActivity(Intent(this,GiangVienActivity::class.java))
        }
        card_add_nhom.setOnClickListener {
            startActivity(Intent(this,NhomActivity::class.java))
        }
        card_add_buoihoc.setOnClickListener {
            startActivity(Intent(this,BuoiHocActivity::class.java))
        }
    }
}