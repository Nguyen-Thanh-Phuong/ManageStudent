package com.example.managestudent.controller.sinhvien

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.model.SinhVien

class SinhVienHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txvNhom: TextView = itemView.findViewById(R.id.txvNhom)
    var txvMH: TextView = itemView.findViewById(R.id.txvMH)
    var txvGV: TextView = itemView.findViewById(R.id.txvGV)
    var rootNhom: ConstraintLayout = itemView.findViewById(R.id.rootNhomItem)
    fun bind(sv: SinhVien) {
        val tenNhom = sv.tenSV
        val tenMH = LopController.instance.getLopName(sv.maLop)
        txvNhom.text = tenNhom
        txvMH.text = tenMH
        val khoaCode =LopController.instance.list.find {
            it.MaLop==sv.maLop
        }?.MaKhoa
        txvGV.text = khoaCode?.let { KhoaController.instances.getKhoaName(it) }
    }
}
