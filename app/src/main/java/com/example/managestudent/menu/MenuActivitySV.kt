package com.example.managestudent.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.managestudent.R
import com.example.managestudent.View.DKiMonHocActivity
import kotlinx.android.synthetic.main.activity_menu_sv.*

class MenuActivitySV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_sv)

        card_calendar.setOnClickListener {
            startActivity(Intent(this,DKiMonHocActivity::class.java))
        }
    }
}