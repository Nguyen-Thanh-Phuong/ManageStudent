package com.example.managestudent.controller.monhoc

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.model.MonHoc

class MonHocHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var root:LinearLayout?=null
    var title: TextView? =null
    init {
        root = itemView.findViewById(R.id.monhocItem)
        title = itemView.findViewById(R.id.monhocTxv)
    }
    fun bind(monHoc:String,drawable:Drawable)
    {
        title?.text = monHoc
        title?.setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
    }
}