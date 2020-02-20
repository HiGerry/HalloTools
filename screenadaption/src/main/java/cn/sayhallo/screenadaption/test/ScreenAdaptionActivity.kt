package cn.sayhallo.screenadaption.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.screenadaption.R
import cn.sayhallo.screenadaption.test.ui.ScreenAdaptionTestActivity
import cn.sayhallo.screenadaption.test.ui.adapter.ScreenAdaptionAdapter

class ScreenAdaptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_adaption)
        HalloStatusBar.setActivityAdapter(this)
        val rvTitleScreenAdaption: RelativeLayout = findViewById(R.id.rv_title_screen_adapter)
        HalloStatusBar.setTopAdater(resources,rvTitleScreenAdaption)

        val rvScreenAdapter: RecyclerView = findViewById(R.id.rv_screen_adapter)
        rvScreenAdapter.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        val adapter = ScreenAdaptionAdapter(getData())
        rvScreenAdapter.adapter = adapter
        adapter.listener = object :ScreenAdaptionAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when(position) {
                    0 -> ScreenAdaptionTestActivity().intoActivity(this@ScreenAdaptionActivity,null)
                }
            }

            override fun onItemLongClick(view: View, position: Int) {
            }

        }
    }

    private fun getData(): Array<out String>? {
        return resources.getStringArray(R.array.adapter_title)

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