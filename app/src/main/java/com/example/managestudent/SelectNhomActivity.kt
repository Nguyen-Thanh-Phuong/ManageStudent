package com.example.managestudent

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.controller.ctbh.CTBHController
import com.example.managestudent.controller.nhom.NhomAdapter
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.nhom.NhomInterface
import com.example.managestudent.model.CTBH
import com.example.managestudent.model.Nhom
import com.example.managestudent.model.SinhVien
import com.google.firebase.database.core.view.View
import kotlinx.android.synthetic.main.activity_select_nhom.*

class SelectNhomActivity : AppCompatActivity(),NhomInterface {
    lateinit var btnBack:Button
    lateinit var recycle:RecyclerView
    lateinit var sv:SinhVien
    lateinit var btnMore:Button
    lateinit var textView: TextView
    var isSelect:Boolean =false
    var instancesNhom = NhomController.getInstance(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_nhom)
        sv = SinhVien("-MIro-WbytXKw_1Rfv3e","Nguyễn Thanh Phuong","2000","-MIqUCQhjMqJdl-725BM")

        textView= findViewById(R.id.txvPlan)
        btnMore = findViewById(R.id.more)
        setView()
        registerForContextMenu(btnMore)

    }

    private fun setView() {
        btnBack = findViewById<LinearLayout>(R.id.btnBack_SLNHOM).findViewById(R.id.btn)
        btnBack.setOnClickListener {
            finish()
        }
        recycle = findViewById(R.id.recycler)
        recycle.layoutManager = LinearLayoutManager(this)
        setDataNhomSelect()

    }
    private fun adapterNhom(list: MutableList<Nhom>){
        recycle.adapter = NhomAdapter(this,list)
        (recycle.adapter as NhomAdapter).notifyDataSetChanged()
    }

    override fun onResume() {

        super.onResume()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: android.view.View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.setHeaderTitle("Context Menu")
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.layout_context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun getListNhom(list: MutableList<Nhom>) {
        if(isSelect)
            setDataNhomSelect()
        else
            setDataNhomNonSelect()
    }

    override fun getCodeSelect(NhomCode: String) {
        CTBHController.instances.insert(CTBH(sv.maSV, NhomCode))
    }
    private fun setDataNhomSelect()
    {
        isSelect = true
        textView.text = "Selected"
        val listCTBH =CTBHController.instances.list.filter {
            sv.maSV==it.maSV
        }
        val listResult =instancesNhom.list.filter {itNhom->
            listCTBH.find {
                it.maMH==itNhom.MaNhom
            }!=null
        } as MutableList<Nhom>
        adapterNhom(listResult)
    }
    private fun setDataNhomNonSelect()
    {
        isSelect =false
        textView.text = "Non Select"
        val listCTBH =CTBHController.instances.list.filter {
            sv.maSV==it.maSV
        }
        val listResult =instancesNhom.list.filter {itNhom->
            listCTBH.find {
                it.maMH==itNhom.MaNhom
            }==null
        }as MutableList<Nhom>
        adapterNhom(listResult)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nhom_select -> setDataNhomSelect()
            R.id.nhom_non_select -> setDataNhomNonSelect()
        }
        return true
    }
}