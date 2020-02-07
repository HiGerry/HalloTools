package cn.sayhallo.halloimmersive

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.view.View
import android.view.WindowManager

import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment

import com.google.android.material.navigation.NavigationView

object HalloStatusBar {

    /*
      测试手机  谷歌安卓模拟器  侧边栏为DrawerLayout时，设置这个属性实现沉浸式   可用
    */

    fun setFragmentAdapter(fragment: Fragment, rootView: View, headerIsImage: Boolean) {
        /*
       适配安卓4.4--5.0
        */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = fragment.activity!!.resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val height = fragment.activity!!.resources.getDimensionPixelSize(resourceId)
            rootView.setPadding(0, height, 0, 0)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = fragment.activity!!.resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val height = fragment.activity!!.resources.getDimensionPixelSize(resourceId)
            rootView.setPadding(0, height, 0, 0)
        }
    }

    /*
    toolbar.getBackground()
     */
    fun setNavigationViewAdapter(
        drawer: DrawerLayout,
        context: Context,
        navigationView: NavigationView
    ) {
        /*
        适配安卓4.4--5.0
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            //将侧边栏顶部延伸至status bar
            drawer.fitsSystemWindows = true
            //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
            drawer.clipToPadding = false
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resourceId =
                context.resources.getIdentifier("status_bar_height", "dimen", "android")
            val height = context.resources.getDimensionPixelSize(resourceId)
            val headerView = navigationView.getHeaderView(0)
            navigationView.foreground = BitmapDrawable()
            //            rootLayout.setPadding(0, height, 0, 0);
            headerView.setPadding(
                headerView.paddingLeft,
                headerView.paddingTop + height,
                headerView.paddingRight,
                headerView.paddingBottom
            )
            //            rootLayout.setBackground(bg);
        }/*
        适配安卓5.0以上
         */
    }

    fun setActivityAdapter(activity: Activity, isSetLightStatusBar: Boolean) {
        /*
        适配安卓4.4--5.0
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            //注意要清除 FLAG_TRANSLUCENT_STATUS flag
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = Color.parseColor("#00000000")

            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }

        /*
               适配6.0以上状态栏文字颜色,设置为深色
            */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && isSetLightStatusBar) {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        } else {
            activity.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }
}
