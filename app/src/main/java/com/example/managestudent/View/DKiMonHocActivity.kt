package com.example.managestudent.View

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.R
import com.example.managestudent.controller.buoihoc.BuoiHocAdapter
import com.example.managestudent.controller.buoihoc.BuoiHocController
import com.example.managestudent.controller.ctbh.CTBHController
import com.example.managestudent.controller.day_of_week.DayOfWeekController
import com.example.managestudent.controller.nhom.NhomController
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.model.BuoiHoc
import com.example.managestudent.model.CTBH
import com.example.managestudent.model.ChiTietNhomHoc
import com.example.managestudent.model.SinhVien

class DKiMonHocActivity : AppCompatActivity() {
    private var list_btn:MutableList<Button> = mutableListOf()
    private var list_btn_Tiet:MutableList<Button> = mutableListOf()
    private var pressBtn:Button?=null
    lateinit var list_TietHoc_OnDay_Not_Select:List<BuoiHoc>
    lateinit var list_TietHoc_OnDay:List<BuoiHoc>
    lateinit var list_CTBH:List<CTBH>
    private var instanceBH = BuoiHocController.instances
    private var instanceCTBH = CTBHController.instances
    lateinit var sv:SinhVien

    private var instanceSV = SinhVienController.instance
    private var NhomResultCode:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_d_ki_mon_hoc)
        sv = SinhVien("-MIro-WbytXKw_1Rfv3e","Nguyễn Thanh Phuong","2000","-MIqUCQhjMqJdl-725BM")
        getCTBHQuerySV()
        setClickDrawable()

//        instanceSV.list.filter {
//            it.maLop=="-MIqUqwxRn43_vTxFA1q"
//        }.forEach {
//            Log.d("MAINN",it.tenSV)
//        }

    }

    private fun getCTBHQuerySV(){
        list_CTBH = instanceCTBH.getListCBTHBySVCode(sv.maSV)
    }

    private fun setData(button: Button)
    {
        val dayCode = DayOfWeekController.instances.getDayCode(list_btn.indexOf(button))
        list_TietHoc_OnDay = BuoiHocController.instances.getListQueryAddBuoiHocByDay(dayCode)
        list_TietHoc_OnDay_Not_Select = list_TietHoc_OnDay.filter {itBH->
                list_CTBH.find {
                    it.maMH == itBH.maNhom
                }==null
        }
        list_TietHoc_OnDay_Not_Select.forEach {
            Log.d("MAINN","${NhomController.instances.getNhomToString(it.maNhom)} -- ${it.tietStart} --${it.tietEnd} -Tiet State: " +
                    "${instanceBH.checkIndexTimeTiet(it)}")
        }
    }


    private fun setClickDrawable()
    {
        list_btn_Tiet.add(findViewById(R.id.lesson1_4))
        list_btn_Tiet.add(findViewById(R.id.lesson5_8))
        list_btn_Tiet.add(findViewById(R.id.lesson9_13))
        list_btn_Tiet.add(findViewById(R.id.lesson_other))
        list_btn_Tiet.forEach { itTiet->
            itTiet.setOnClickListener {
                startLoadingDialog(list_TietHoc_OnDay_Not_Select.filter {
                    instanceBH.checkIndexTimeTiet(it)==list_btn_Tiet.indexOf(itTiet)
                } as MutableList<BuoiHoc>)
            }
        }

        list_btn.add(findViewById(R.id.btn_mon))
        list_btn.add(findViewById(R.id.btn_tue))
        list_btn.add(findViewById(R.id.btn_wed))
        list_btn.add(findViewById(R.id.btn_thu))
        list_btn.add(findViewById(R.id.btn_fri))
        list_btn.add(findViewById(R.id.btn_sat))

        list_btn.forEach { it ->
            it.setOnClickListener {
                pressBtn?.setBackgroundResource(R.drawable.background_button)
                it.setBackgroundResource(R.drawable.button_selector)
                pressBtn = it as Button?
                setData(it as Button)
            }
        }
        //
        list_btn[0].let {
            pressBtn?.setBackgroundResource(R.drawable.background_button)
            it.setBackgroundResource(R.drawable.button_selector)
            pressBtn = it as Button?
            setData(it as Button)
        }
    }
    fun startLoadingDialog(listBH:MutableList<BuoiHoc>)
    {
        NhomResultCode = ""
        var builder: AlertDialog.Builder = AlertDialog.Builder(this);

        val inflater = this.layoutInflater
        val view = inflater.inflate(R.layout.custom_dialog_buoi_hoc,null)

        val textView = view.findViewById<TextView>(R.id.txvDisplayBH)
        textView.hint=""
        val recycle = view.findViewById<RecyclerView>(R.id.recycler)
        recycle.adapter = BuoiHocAdapter(this,listBH,textView)
        recycle.layoutManager = LinearLayoutManager(this)

        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->

        })
        builder.setPositiveButton("Insert", DialogInterface.OnClickListener { dialogInterface, i ->
            NhomResultCode = textView.hint.toString()
            if(NhomResultCode.isEmpty())
                Toast.makeText(this,"Please Select Nhom Hoc",Toast.LENGTH_SHORT).show()
            else
            {
                CTBHController.instances.insert(CTBH(sv.maSV, NhomResultCode!!))
            }

        })
        builder.setView(view)
        builder.setCancelable(true)
        val alertDialog = builder.create()
        alertDialog?.show()
    }
}