package com.example.managestudent.controller.buoihoc

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.Nhom

class BuoiHocAdapter(
    var context: Context,
    var lData: MutableList<BuoiHoc>,
    var textView:TextView?=null
) : RecyclerView.Adapter<BuoiHocHolder>() {
    var rootSelected:ImageView?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuoiHocHolder {

        val inflater = LayoutInflater
            .from(context)
        val view = inflater.inflate(R.layout.sub_item_nhom, parent, false)
        return BuoiHocHolder(view)
    }

    override fun onBindViewHolder(holder: BuoiHocHolder, position: Int) {
        when (holder) {
            is BuoiHocHolder -> {
                holder.bind(lData[position])
                holder.root.setOnClickListener {
                    val value = lData[position]
                    holder.bind(value)
                    holder.root.setOnClickListener {
                        rootSelected?.setBackgroundColor(ContextCompat.getColor(context,R.color.normal))
                        holder.imgView.setBackgroundColor(ContextCompat.getColor(context,R.color.color_btn_1))
                        rootSelected = holder.imgView
                        textView?.hint = value.maNhom
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}