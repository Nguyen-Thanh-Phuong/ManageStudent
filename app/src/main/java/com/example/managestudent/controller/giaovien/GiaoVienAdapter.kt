package com.example.managestudent.controller.giaovien

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.monhoc.MonHocHolder

class GiaoVienAdapter(var context: Context,
                      var lData:MutableList<String>) : RecyclerView.Adapter<MonHocHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonHocHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.single_item, parent, false)
        return MonHocHolder(view)
    }

    override fun onBindViewHolder(holder: MonHocHolder, position: Int) {
        when (holder) {
            is MonHocHolder -> {
                var drawable : Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_book_48)
                drawable?.let { holder.bind(lData[position], it) }
                holder.root?.setOnClickListener {
                    Log.d("MAINN",position.toString())
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lData.size
    }
}