package com.example.managestudent.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RatingBar
import android.widget.TextView
import com.example.managestudent.R
import com.example.managestudent.model.Khoa
import java.lang.Exception

class HeroAdapter(context:Context,val layoutResId:Int,val list:List<Khoa>)
    :ArrayAdapter<Khoa>(context,layoutResId,list){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var inflater:LayoutInflater = LayoutInflater.from(context)
        var view = inflater.inflate(layoutResId,null)

        val textViewName = view.findViewById<TextView>(R.id.textViewName)
        val textViewId = view.findViewById<TextView>(R.id.idChildList)
        val ratingChild = view.findViewById<RatingBar>(R.id.ratingChild)

        try {
            val hero = list[position]
            textViewName.text = hero.tenKhoa
        }catch (e:Exception){}

        return view
    }
}