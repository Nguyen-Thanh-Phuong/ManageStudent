package com.example.managestudent.controller.buoihoc

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.Nhom

class BuoiHocAdapter(
    var context: Context,
    var lData: MutableList<BuoiHoc>
) : RecyclerView.Adapter<BuoiHocHolder>() {

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
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}