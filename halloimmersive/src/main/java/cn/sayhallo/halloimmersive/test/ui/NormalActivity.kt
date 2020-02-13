package cn.sayhallo.halloimmersive.test.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.halloimmersive.R

class NormalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal_test)
        HalloStatusBar.setActivityAdapter(this)
        val tvTitle:TextView = findViewById(R.id.tv_title)
        HalloStatusBar.setTopAdater(resources,tvTitle)
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