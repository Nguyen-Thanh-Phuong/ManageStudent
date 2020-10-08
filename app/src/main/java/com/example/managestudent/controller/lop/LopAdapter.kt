package com.example.managestudent.controller.lop

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.buoihoc.BuoiHocAdapter
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.Lop
import com.example.managestudent.model.Nhom

class LopAdapter(
    var context: Context,
    var lData: MutableList<Lop>
) : RecyclerView.Adapter<LopHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LopHolder {

        val inflater = LayoutInflater
            .from(context)
        val view = inflater.inflate(R.layout.sub_item_nhom, parent, false)
        return LopHolder(view)
    }

    override fun onBindViewHolder(holder: LopHolder, position: Int) {
        when (holder) {
            is LopHolder -> {
                holder.bind(lData[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}