package com.example.managestudent.View

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.managestudent.R

class DKiMonHocActivity : AppCompatActivity() {
    var list_btn:MutableList<Button> = mutableListOf()
    var pressBtn:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_ki_mon_hoc)
        setClickDrawable()
    }
    fun setClickDrawable()
    {
        list_btn.add(findViewById(R.id.btn_mon))
        list_btn.add(findViewById(R.id.btn_tue))
        list_btn.add(findViewById(R.id.btn_wed))
        list_btn.add(findViewById(R.id.btn_thu))
        list_btn.add(findViewById(R.id.btn_fri))
        list_btn.add(findViewById(R.id.btn_sat))

        list_btn.forEach { it ->
            it.setOnClickListener {
                pressBtn?.setBackgroundResource(R.drawable.background_button)
                it.setBackgroundResource(R.drawable.button_selector)
                pressBtn = it as Button?
            }
        }
    }
}