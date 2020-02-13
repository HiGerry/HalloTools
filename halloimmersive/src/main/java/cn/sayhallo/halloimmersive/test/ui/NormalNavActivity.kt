package cn.sayhallo.halloimmersive.test.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.widget.RelativeLayout
import android.widget.TextView
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.halloimmersive.R

class NormalNavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_nav)
        HalloStatusBar.setActivityAdapter(this)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val tvNavTitle: TextView = findViewById(R.id.tv_nav_title)
        val navHeader:RelativeLayout = navView.getHeaderView(0).findViewById(R.id.nav_header)
        HalloStatusBar.setNavigationViewTopAdapter(drawerLayout)
        HalloStatusBar.setTopAdater(resources,tvNavTitle)
        HalloStatusBar.setTopAdater(resources,navHeader)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.normal_nav, menu)
        return true
    }

    fun intoActivity(context: Context, bundle: Bundle?) {
        val intent = Intent(context, this::class.java)
        if (bundle == null) {
            context.startActivity(intent)
        } else {
            context.startActivity(intent, bundle)
        }
    }
}
