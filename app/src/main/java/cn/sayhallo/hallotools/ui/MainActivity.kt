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
import androidx.appcompat.app.AppCompatDelegate
import cn.sayhallo.hallo_annotation.BindView
import cn.sayhallo.hallo_butterknife.HalloButterKnife
import cn.sayhallo.hallotools.R.id.*

class MainActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> navView!!.selectedItemId = navigation_home
            1 -> navView!!.selectedItemId = navigation_dashboard
            2 -> {
                navView!!.selectedItemId = navigation_notifications
            }
        }
    }

    private var viewpager: ViewPager? = null

    @JvmField
    @BindView(id = nav_view)
    var navView:BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HalloStatusBar.setActivityAdapter(this)
        HalloButterKnife.bind(this)

        navView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        viewpager = findViewById(main_viewpager)
        viewpager!!.adapter = ViewPagerAdapter(supportFragmentManager)
        viewpager!!.offscreenPageLimit = 2
        viewpager!!.addOnPageChangeListener(this)
    }

private val mOnNavigationItemSelectedListener =
    BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            navigation_home -> {
                viewpager!!.setCurrentItem(0, false)
                return@OnNavigationItemSelectedListener true
            }
            navigation_dashboard -> {
                viewpager!!.setCurrentItem(1, false)
                return@OnNavigationItemSelectedListener true
            }
            navigation_notifications -> {
                viewpager!!.setCurrentItem(2, false)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private inner class ViewPagerAdapter(fm: FragmentManager) :
        FragmentStatePagerAdapter(fm) {

        private var fragments: ArrayList<Fragment>? = null

        init {
            fragments = ArrayList()
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
