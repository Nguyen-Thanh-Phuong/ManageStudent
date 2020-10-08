package com.example.managestudent.controller.sinhvien

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.model.SinhVien

class SinhVienAdapter (
    var context: Context,
    var lData: MutableList<SinhVien>
) : RecyclerView.Adapter<SinhVienHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SinhVienHolder {

        val inflater = LayoutInflater
            .from(context)
        val view = inflater.inflate(R.layout.sub_item_nhom, parent, false)
        return SinhVienHolder(view)
    }

    override fun onBindViewHolder(holder: SinhVienHolder, position: Int) {
        when (holder) {
            is SinhVienHolder -> {
                holder.bind(lData[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}