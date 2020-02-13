package cn.sayhallo.halloimmersive.test.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.halloimmersive.R
import cn.sayhallo.halloimmersive.test.ui.fragment.NormalFragment
import cn.sayhallo.halloimmersive.test.ui.fragment.TopImageFragment

class MoreFragmentlNavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_frag_nav)
        HalloStatusBar.setActivityAdapter(this)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navHeader:RelativeLayout = navView.getHeaderView(0).findViewById(R.id.nav_header)
        HalloStatusBar.setNavigationViewTopAdapter(drawerLayout)
        HalloStatusBar.setTopAdater(resources,navHeader)
        val vp: ViewPager = findViewById(R.id.vp_fragment)
        vp.adapter = FragmentAdapter(supportFragmentManager,0)
    }

    fun intoActivity(context: Context, bundle: Bundle?) {
        val intent = Intent(context, this::class.java)
        if (bundle == null) {
            context.startActivity(intent)
        } else {
            context.startActivity(intent, bundle)
        }
    }

    private inner class FragmentAdapter(fm: FragmentManager,behavior: Int): FragmentStatePagerAdapter(fm,behavior) {

        var fragments:ArrayList<Fragment>? = null

        init {
            fragments = ArrayList()
            fragments!!.add(NormalFragment())
            fragments!!.add(TopImageFragment())
        }

        override fun getItem(position: Int): Fragment {
            return fragments!![position]
        }

        override fun getCount(): Int {
            return fragments!!.size
        }

    }
}
