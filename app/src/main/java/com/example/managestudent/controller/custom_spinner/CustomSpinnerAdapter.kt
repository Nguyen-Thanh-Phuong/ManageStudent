package com.example.managestudent.controller.custom_spinner

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.managestudent.R
import com.jaredrummler.materialspinner.MaterialSpinnerAdapter
import kotlinx.android.synthetic.main.custom_spinner.view.*

class CustomSpinnerAdapter(private val contextAc: Context, var mutableList: MutableList<CustomSpinnerItem>) :
    ArrayAdapter<CustomSpinnerItem>(contextAc,R.layout.item_spinner1,mutableList)
{
    private fun getCustomView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.d("MAINN","hihi")
        var view = LayoutInflater.from(contextAc).inflate(R.layout.item_spinner1,parent,false)
        val value = mutableList.get(position)
        view.findViewById<TextView>(R.id.txvItem_spinner1).text = value.string1
        view.findViewById<TextView>(R.id.txvItem_spinner2).text = value.string2
        return view!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position,convertView,parent)
    }

}