package com.example.managestudent.controller.day_of_week

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R

class DayOfWeekAdapter(var context: Context,
                       var lData:MutableList<String>) : RecyclerView.Adapter<DayOfWeekHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayOfWeekHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.single_item, parent, false)
        return DayOfWeekHolder(view)
    }

    override fun onBindViewHolder(holder: DayOfWeekHolder, position: Int) {
        when (holder) {
            is DayOfWeekHolder -> {
                var drawable : Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_book_48)
                drawable?.let { holder.bind(lData[position], it) }
                holder.root?.setOnClickListener {
                    Log.d("MAINN",position.toString())
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}