package com.example.managestudent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.View.SinhVienActivity
import com.example.managestudent.controller.lop.LopController
import com.example.managestudent.controller.sinhvien.SinhVienAdapter
import com.example.managestudent.controller.sinhvien.SinhVienController
import com.example.managestudent.controller.sinhvien.SinhVienInterface
import com.example.managestudent.model.SinhVien
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jaredrummler.materialspinner.MaterialSpinner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InformationAccountFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformationAccountFragment : Fragment(), SinhVienInterface {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var edtNameSVLayout: TextInputLayout
    lateinit var edtNameSV : TextInputEditText

    lateinit var edtNamSinhSVLayout: TextInputLayout
    lateinit var edtNamSinhSV : TextInputEditText

    lateinit var spinnerLop: MaterialSpinner
    lateinit var recyclerView: RecyclerView

    val instanceSV = context?.let { SinhVienController.getInstance(it,this) }
    val instanceLop = LopController.instance
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.activity_sinh_vien, container, false)
        //init hint layout
        edtNameSVLayout = root.findViewById<LinearLayout>(R.id.txvNameSV_SV).findViewById(R.id.edt_layout_input)
        edtNameSVLayout.hint = "Nhap Tên SV"

        edtNameSV = root.findViewById<LinearLayout>(R.id.txvNameSV_SV).findViewById(R.id.edt_input)

        edtNamSinhSVLayout = root.findViewById<LinearLayout>(R.id.txvNamSinh_SV).findViewById(R.id.edt_layout_input)
        edtNamSinhSVLayout.hint = "Nhap Năm Sinh"

        edtNamSinhSV = root.findViewById<LinearLayout>(R.id.txvNameSV_SV).findViewById(R.id.edt_input)

        //recycler View init
        recyclerView = root.findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        //init spinner
        spinnerLop = root.findViewById(R.id.customSpinner)
        root.findViewById<Button>(R.id.btn).setOnClickListener {
            insert()
        }
        return root
    }
    private fun insert()
    {
        val groupName = edtNameSV.text.toString()
        val maLop = instanceLop.getLop(spinnerLop.selectedIndex).MaLop
        var sv = SinhVien(groupName,edtNamSinhSV.text.toString(),maLop)
        instanceSV?.insert(sv)
    }
    private fun setAdapter(list: MutableList<SinhVien>)
    {
        val adapter = context?.let { SinhVienAdapter(it, list) }
        recyclerView.adapter = adapter
        adapter?.notifyDataSetChanged()
    }
    override fun onResume() {
        super.onResume()

        spinnerLop.setItems(instanceLop.listLopName)
        if (instanceSV != null) {
            setAdapter(instanceSV.list)
        }

    }


    override fun getListSV(list: MutableList<SinhVien>) {
        setAdapter(list)
    }

    private fun save(view: View)
    {}
    private fun takeAPhoTo(view: View){}
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InformationAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InformationAccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}