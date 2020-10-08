package com.example.managestudent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectNhomActivity : AppCompatActivity() {
    lateinit var btnBack:Button
    lateinit var btnInsert:Button
    lateinit var recycle:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_nhom)

        setView()
    }

    private fun setView() {
        btnBack = findViewById<LinearLayout>(R.id.btnBack_SLNHOM).findViewById(R.id.btn)
        btnInsert = findViewById<LinearLayout>(R.id.btn_Insert_MonHoc).findViewById(R.id.btn)

        recycle = findViewById(R.id.recycler)
        recycle.layoutManager = LinearLayoutManager(this)
    }
}