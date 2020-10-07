package com.example.managestudent.controller.nhom

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.model.MonHoc
import com.example.managestudent.model.Nhom

class NhomHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txvNhom: TextView = itemView.findViewById(R.id.txvNhom)
    var txvMH: TextView = itemView.findViewById(R.id.txvMH)
    var txvGV: TextView = itemView.findViewById(R.id.txvGV)
    fun bind(nhom:Nhom)
    {
        val tenNhom = nhom.TenNhom
        val tenGV = GiaoVienController.instances.getGVName(nhom.MaGV)
        val tenMH = MonHocController.instances.getTenMH(nhom.MaMH)
        txvNhom.text = tenNhom
        txvMH.text =tenMH
        txvGV.text =tenGV
    }
}