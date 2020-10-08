package com.example.managestudent.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.managestudent.R
import kotlinx.android.synthetic.main.activity_menu2.*

class MenuActivityGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu2)
        card_add_data.setOnClickListener {
            startActivity(Intent(this,
                MenuAddActivityGV::class.java))
        }
    }
}