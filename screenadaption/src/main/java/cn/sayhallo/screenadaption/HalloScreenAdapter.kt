package cn.sayhallo.screenadaption

import android.content.ComponentCallbacks
import android.app.Application
import android.app.Activity
import android.content.res.Configuration


object HalloScreenAdapter {

    // 系统的Density
    private var sNoncompatDensity: Float = 0.toFloat()
    // 系统的ScaledDensity
    private var sNoncompatScaledDensity: Float = 0.toFloat()

    public fun setCustomDensity(activity: Activity, application: Application) {
        val displayMetrics = application.resources.displayMetrics
        if (sNoncompatDensity == 0f) {
            sNoncompatDensity = displayMetrics.density
            sNoncompatScaledDensity = displayMetrics.scaledDensity
            // 监听在系统设置中切换字体
            application.registerComponentCallbacks(object : ComponentCallbacks {
                override fun onConfigurationChanged(newConfig: Configuration?) {
                    if (newConfig != null && newConfig!!.fontScale > 0) {
                        sNoncompatScaledDensity = application.resources.displayMetrics.scaledDensity
                    }
                }

                override fun onLowMemory() {

                }
            })
        }
        // 此处以420dp的设计图作为例子
        val targetDensity = (displayMetrics.widthPixels / 411.43).toFloat()
        val targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity)
        val targetDensityDpi = (160 * targetDensity).toInt()
        displayMetrics.density = targetDensity
        displayMetrics.scaledDensity = targetScaledDensity
        displayMetrics.densityDpi = targetDensityDpi

        val activityDisplayMetrics = activity.resources.displayMetrics
        activityDisplayMetrics.density = targetDensity
        activityDisplayMetrics.scaledDensity = targetScaledDensity
        activityDisplayMetrics.densityDpi = targetDensityDpi
    }
}
