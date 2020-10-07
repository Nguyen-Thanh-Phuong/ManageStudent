package com.example.managestudent.controller.day_of_week

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R

class DayOfWeekHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var root: LinearLayout?=null
    var title: TextView? =null
    init {
        root = itemView.findViewById(R.id.monhocItem)
        title = itemView.findViewById(R.id.monhocTxv)
    }
    fun bind(mdata:String,drawable: Drawable)
    {
        title?.text = mdata
        title?.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }
}
