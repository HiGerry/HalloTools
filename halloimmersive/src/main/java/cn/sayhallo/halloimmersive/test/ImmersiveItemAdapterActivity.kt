package cn.sayhallo.halloimmersive.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.sayhallo.halloimmersive.R

class ImmersiveItemAdapterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersive_item)
    }

    fun intoActivity(context: Context,bundle: Bundle?) {
        if (bundle == null) {
            context.startActivity(Intent(context,ImmersiveItemAdapterActivity::class.java))
        }else{
            context.startActivity(Intent(context,ImmersiveItemAdapterActivity::class.java),bundle)
        }
    }



}
