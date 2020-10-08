package com.example.managestudent.controller.nhom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.model.Nhom

class NhomAdapter(var context: Context,
                  var lData:MutableList<Nhom>) : RecyclerView.Adapter<NhomHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NhomHolder {

        val inflater = LayoutInflater
            .from(context)
        val view= inflater.inflate(R.layout.sub_item_nhom, parent, false)
        return NhomHolder(view)
    }

    override fun onBindViewHolder(holder: NhomHolder, position: Int) {
        when (holder) {
            is NhomHolder -> {
                holder.bind(lData[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}