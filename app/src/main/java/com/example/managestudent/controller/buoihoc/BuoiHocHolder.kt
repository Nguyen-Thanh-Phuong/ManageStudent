package com.example.managestudent.controller.buoihoc

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.DayOfWeek
import com.example.managestudent.model.Nhom

class BuoiHocHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txvNhom: TextView = itemView.findViewById(R.id.txvNhom)
    var txvMH: TextView = itemView.findViewById(R.id.txvMH)
    var txvGV: TextView = itemView.findViewById(R.id.txvGV)
    var imgView:ImageView = itemView.findViewById(R.id.img_background)
    var root:ConstraintLayout = itemView.findViewById(R.id.rootNhomItem)
    fun bind(buoiHoc: BuoiHoc)
    {
        val string1 = DayOfWeekController.instances.getDayName(buoiHoc.maDay)
        val string2 = NhomController.instances.getNhomToString(buoiHoc.maNhom)
        txvNhom.text = string2
        txvMH.text =string1
        txvGV.text = "Tiet ${buoiHoc.tietStart} - Tiet ${buoiHoc.tietEnd}"
    }
}