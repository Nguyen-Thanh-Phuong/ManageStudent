package com.example.managestudent.controller.lop

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.giaovien.GiaoVienController
import com.example.managestudent.controller.khoa.KhoaController
import com.example.managestudent.controller.monhoc.MonHocController
import com.example.managestudent.model.Lop
import com.example.managestudent.model.Nhom

class LopHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txvNhom: TextView = itemView.findViewById(R.id.txvNhom)
    var txvMH: TextView = itemView.findViewById(R.id.txvMH)
    var txvGV: TextView = itemView.findViewById(R.id.txvGV)
    var rootNhom: ConstraintLayout = itemView.findViewById(R.id.rootNhomItem)
    fun bind(lop: Lop) {
        val tenNhom = lop.TenLop
        val tenMH = KhoaController.instances.getKhoaName(lop.MaKhoa)
        txvNhom.text = tenNhom
        txvMH.text = tenMH
        txvGV.text = ""
    }
}
