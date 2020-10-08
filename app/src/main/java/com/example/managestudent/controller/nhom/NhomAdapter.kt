package com.example.managestudent.controller.nhom

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.buoihoc.BuoiHocAdapter
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.controller.ctbh.CTBHController
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.CTBH
import com.example.managestudent.model.Nhom

class NhomAdapter(
    var context: Context,
    var lData: MutableList<Nhom>,
    var nhomInterface: NhomInterface?=null
) : RecyclerView.Adapter<NhomHolder>() {
    var NhomCode: String = ""
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhomHolder {

        val inflater = LayoutInflater
            .from(context)
        val view = inflater.inflate(R.layout.sub_item_nhom, parent, false)
        return NhomHolder(view)
    }

    override fun onBindViewHolder(holder: NhomHolder, position: Int) {
        val values = lData[position]
        when (holder) {
            is NhomHolder -> {
                holder.bind(lData[position])
                holder.rootNhom.setOnClickListener {
                    startLoadingDialog(BuoiHocController.instances.list.filter {
                        it.maNhom == values.MaNhom
                    } as MutableList<BuoiHoc>, position)
                    nhomInterface?.getCodeSelect(NhomCode)
                }
            }
        }
    }

    fun startLoadingDialog(listBH: MutableList<BuoiHoc>, position: Int) {
        var builder: AlertDialog.Builder = AlertDialog.Builder(context);

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_dialog_buoi_hoc, null)

        val textView = view.findViewById<TextView>(R.id.txvDisplayBH)
        textView.hint = ""
        val recycle = view.findViewById<RecyclerView>(R.id.recycler)
        recycle.adapter = BuoiHocAdapter(context, listBH, textView)
        recycle.layoutManager = LinearLayoutManager(context)

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

        })
            .setPositiveButton("Insert", DialogInterface.OnClickListener { dialogInterface, i ->
                NhomCode = lData[position].MaNhom
            })
        builder.setView(view)
        builder.setCancelable(true)
        val alertDialog = builder.create()
        alertDialog?.show()
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}