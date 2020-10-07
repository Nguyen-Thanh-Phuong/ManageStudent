package com.example.managestudent.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.day_of_week.DayOfWeekAdapter
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.day_of_week.DayOfWeekInterface
import com.example.managestudent.controller.giaovien.GiaoVienAdapter
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.model.DayOfWeek
import com.example.managestudent.model.GiaoVien
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

class DayOfWeekActivity : AppCompatActivity(),DayOfWeekInterface {
    lateinit var instances:DayOfWeekController;
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_day_of_week)

        //set Name Edittext
        findViewById<TextInputLayout>(R.id.edt_layout_input).hint = "Input Day Of Week"

        instances = DayOfWeekController.getInstance(this,this)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
    }
    fun insert()
    {
        val textView = findViewById<TextView>(R.id.edt_input)
        val tenDay = textView.text.toString()
        var Day = DayOfWeek(tenDay)
        instances.insert(Day)
    }

    override fun getListDayOfWeek(list: MutableList<String>) {
        val adapter = DayOfWeekAdapter(this,list)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}