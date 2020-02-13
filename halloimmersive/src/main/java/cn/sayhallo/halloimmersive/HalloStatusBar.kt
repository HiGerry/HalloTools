package cn.sayhallo.halloimmersive

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.drawerlayout.widget.DrawerLayout

object HalloStatusBar {

    fun setTopAdater(resources:Resources,headerView: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val statusBarHeight = resources.getDimensionPixelSize(resourceId)
            headerView.setPadding(0, statusBarHeight, 0, 0)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val statusBarHeight = resources.getDimensionPixelSize(resourceId)
            headerView.setPadding(0, 0 + statusBarHeight, 0, 0)
        }
    }

    fun setNavigationViewTopAdapter(drawer: DrawerLayout) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawer.fitsSystemWindows = true
            drawer.clipToPadding = false
        }
    }

    fun setActivityAdapter(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = Color.parseColor("#00000000")
            //防止底部虚拟导航栏透明化
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}
