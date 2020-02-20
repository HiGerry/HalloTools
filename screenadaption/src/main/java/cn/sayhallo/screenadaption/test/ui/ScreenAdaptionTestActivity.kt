package cn.sayhallo.screenadaption.test.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.screenadaption.HalloScreenAdapter
import cn.sayhallo.screenadaption.R

class ScreenAdaptionTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        HalloScreenAdapter.setCustomDensity(this,application)
        setContentView(R.layout.activity_screen_adaption_test)
        HalloStatusBar.setActivityAdapter(this)
        val rvTitle:TextView = findViewById(R.id.tv_screen_adaption_title)
        HalloStatusBar.setTopAdater(resources,rvTitle)
    }

    fun intoActivity(context: Context, bundle: Bundle?) {
        val intent = Intent(context,this::class.java)
        if (bundle == null) {
            context.startActivity(intent)
        }else{
            context.startActivity(intent,bundle)
        }
    }
}