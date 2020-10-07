package com.example.managestudent.controller.day_of_week

import android.content.Context
import android.widget.Toast
import com.example.managestudent.model.DayOfWeek
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DayOfWeekController {
    var list:MutableList<DayOfWeek> = mutableListOf()
    var listDayOfWeekCode:MutableList<String> = mutableListOf()
    var listDayOfWeekName:MutableList<String> = mutableListOf()
    private lateinit var context: Context
    private var dayOfWeekInterface: DayOfWeekInterface?=null
    private constructor(){initDayOfWeek()}
    companion object
    {
        val instances =
            DayOfWeekController()
        fun getInstance(context: Context): DayOfWeekController {
            instances.context = context
            return instances
        }
        fun getInstance(context: Context, DayOfWeekInterface: DayOfWeekInterface): DayOfWeekController {
            getInstance(
                context
            ).dayOfWeekInterface = DayOfWeekInterface
            return instances
        }
    }
    private fun initDayOfWeek()
    {
        val firebase = getFirebaseInstance()
        firebase.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                listDayOfWeekCode.clear()
                listDayOfWeekName.clear()
                list.clear()
                if(p0.exists())
                {
                    for(data in p0.children)
                    {
                        val value = data.getValue(DayOfWeek::class.java)
                        if(value!=null)
                        {
                            listDayOfWeekCode.add(value.MaDay)
                            listDayOfWeekName.add(value.TenDay)
                            list.add(value)
                        }
                    }
                    dayOfWeekInterface?.getListDayOfWeek(listDayOfWeekName)
                }
            }
        })
    }
    fun getDayName(code:String) = listDayOfWeekName.get(listDayOfWeekCode.indexOf(code))
    fun getDayCode(index: Int): String {
        return listDayOfWeekCode[index]
    }
    fun getPos(MaDay:String): Int {
        return listDayOfWeekCode.indexOf(MaDay)
    }
    fun getDayOfWeek(index:Int): DayOfWeek {
        return list[index]
    }
    fun getFirebaseInstance() = FirebaseDatabase.getInstance().getReference("DayOfWeek")
    fun getListDay() = listDayOfWeekName
    fun insert(DayOfWeek: DayOfWeek)
    {
        if(list.find { it.TenDay ==DayOfWeek.TenDay }!=null)
        {
            Toast.makeText(context,"DayOfWeek ${DayOfWeek.TenDay} Already", Toast.LENGTH_SHORT).show();
        }
        val firebase = getFirebaseInstance()
        val key = firebase.push().key
        if(key!=null)
        {
            DayOfWeek.MaDay =key
            firebase.child(key).setValue(DayOfWeek).addOnCompleteListener {
                Toast.makeText(context,"Insert DayOfWeek ${DayOfWeek.TenDay} Success", Toast.LENGTH_SHORT).show();
            }
        }
    }
}