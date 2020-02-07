package cn.sayhallo.hallotools.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.hallotools.R
import cn.sayhallo.hallotools.ui.my.MyFragment
import cn.sayhallo.hallotools.ui.sharelife.SharedLifeFragment
import cn.sayhallo.hallotools.ui.tools.ToolsFragment
import java.util.ArrayList

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> nav_view!!.setSelectedItemId(R.id.navigation_home)
            1 -> nav_view!!.setSelectedItemId(R.id.navigation_dashboard)
            2 -> nav_view!!.setSelectedItemId(R.id.navigation_notifications)
        }
    }

    var viewpager: ViewPager? = null
    var nav_view:BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HalloStatusBar.setActivityAdapter(this,true)

        nav_view = findViewById(R.id.nav_view)
        nav_view!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewpager = findViewById(R.id.main_viewpager)
        viewpager!!.adapter = ViewPagerAdapter(supportFragmentManager, 0)
        viewpager!!.offscreenPageLimit = 2
        viewpager!!.addOnPageChangeListener(this)
    }

private val mOnNavigationItemSelectedListener =
    BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager!!.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewpager!!.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewpager!!.setCurrentItem(2, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
        FragmentStatePagerAdapter(fm) {

        private var fragments: ArrayList<Fragment>? = null

        init {
            fragments = ArrayList<Fragment>()
            fragments!!.add(ToolsFragment())
            fragments!!.add(SharedLifeFragment())
            fragments!!.add(MyFragment())
        }

        override fun getItem(position: Int): Fragment {
            return fragments!![position]
        }

        override fun getCount(): Int {
            return fragments!!.size
        }
    }
}
