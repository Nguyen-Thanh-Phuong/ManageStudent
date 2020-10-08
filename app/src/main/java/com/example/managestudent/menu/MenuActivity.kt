package com.example.managestudent.menu

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.managestudent.HelperClasses.adapterphone
import com.example.managestudent.HelperClasses.phonehelper
import com.example.managestudent.R
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import jp.wasabeef.recyclerview.animators.ScaleInAnimator
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import kotlinx.android.synthetic.main.activity_menu.*
import www.sanju.zoomrecyclerlayout.ZoomRecyclerLayout
class MenuActivity : AppCompatActivity(), adapterphone.ListItemClickListener {
    var phoneRecycler: RecyclerView? = null
    var adapter: RecyclerView.Adapter<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        rela_Calendar.setOnClickListener {
            val intent = Intent(this,CalendarActivity::class.java)
            startActivity(intent)
        }
        //Hooks
        phoneRecycler = findViewById(R.id.my_recycler)
        phoneRecycler()
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
        phoneRecycler!!.layoutManager = ZoomRecyclerLayout(this,LinearLayoutManager.HORIZONTAL,false)
//        phoneRecycler!!.layoutManager = LinearLayoutManager(
//            this,
//            LinearLayoutManager.HORIZONTAL,
//            false
//        )

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

    override fun onphoneListClick(clickedItemIndex: Int) {
//            Intent mIntent;
//            switch (clickedItemIndex){
//                case 0: //first item in Recycler view
//                    mIntent  = new Intent(FirstActivity.this, samsung.class);
//                    startActivity(mIntent);
//                    break;
//                case 1: //second item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, vivo.class);
//                    startActivity(mIntent);
//                    break;
//                case 2: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, apple.class);
//                    startActivity(mIntent);
//                    break;
        //              case 3: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, realme.class);
//                    startActivity(mIntent);
//                    break;
//                case 4: //third item in Recycler view
//                    mIntent = new Intent(FirstActivity.this, poco.class);
//                    startActivity(mIntent);
//                    break;
//
//        }
    }
}