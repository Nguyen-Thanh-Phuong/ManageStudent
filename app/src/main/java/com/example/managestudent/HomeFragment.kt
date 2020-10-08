package com.example.managestudent

import android.content.Context
import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.HelperClasses.adapterphone
import com.example.managestudent.HelperClasses.phonehelper
import com.example.managestudent.menu.CalendarActivity
import com.example.managestudent.menu.MenuActivity
import kotlinx.android.synthetic.main.activity_menu.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), adapterphone.ListItemClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var phoneRecycler: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null
    private lateinit var calender: RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        //Hooks

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        phoneRecycler = root.findViewById(R.id.my_recycler)
        calender  = root.findViewById(R.id.rela_Calendar)
        calender.setOnClickListener {
                val intent = Intent(activity, CalendarActivity::class.java)
                startActivity(intent)
        }
        phoneRecycler()
        return root
    }
    private fun phoneRecycler() {

        //All Gradients
        val gradient2 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x2b341b, -0x2b341b)
        )
        val gradient1 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x852331, -0x852331)
        )
        val gradient3 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x83a61, -0x83a61)
        )
        val gradient4 = GradientDrawable(
            GradientDrawable.Orientation.LEFT_RIGHT,
            intArrayOf(-0x47280b, -0x47280b)
        )
        phoneRecycler!!.setHasFixedSize(true)
        //phoneRecycler!!.layoutManager = ZoomRecyclerLayout(activity!!.applicationContext ,LinearLayoutManager.HORIZONTAL,false)
        phoneRecycler!!.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val phonelocations: ArrayList<phonehelper> = ArrayList()
        phonelocations.add(phonehelper(gradient1, R.drawable.johnwick1, "johnwick1"))
        phonelocations.add(phonehelper(gradient4, R.drawable.deadpool, "Deadpool"))
        phonelocations.add(phonehelper(gradient2, R.drawable.donaldtrump, "donaldtrump"))
        phonelocations.add(phonehelper(gradient4, R.drawable.johnwick2, "johnwick2"))
        //phonelocations.add(phonehelper(gradient2, R.drawable.poco, "Poco"))
        adapter = adapterphone(phonelocations, this)
        phoneRecycler!!.adapter = adapter
        PagerSnapHelper().attachToRecyclerView(phoneRecycler)
    }

    override fun onphoneListClick(clickedItemIndex: Int) {}
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}