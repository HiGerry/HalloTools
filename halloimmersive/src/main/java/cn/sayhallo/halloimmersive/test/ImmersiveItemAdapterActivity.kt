package cn.sayhallo.halloimmersive.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.halloimmersive.R
import cn.sayhallo.halloimmersive.test.ui.adapter.ImmersiveItemAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.sayhallo.halloimmersive.HalloStatusBar.setActivityAdapter
import cn.sayhallo.halloimmersive.test.ui.NormalActivity
import cn.sayhallo.halloimmersive.test.ui.NormalNavActivity
import cn.sayhallo.halloimmersive.test.ui.MoreFragmentActivity
import cn.sayhallo.halloimmersive.test.ui.MoreFragmentlNavActivity

class ImmersiveItemAdapterActivity : AppCompatActivity() {

    private lateinit var rvImmersive: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_immersive_item)
        setActivityAdapter(this)
        val rvTitleImmersive:RelativeLayout = findViewById(R.id.rv_title_immersive)
        HalloStatusBar.setTopAdater(resources,rvTitleImmersive)

        rvImmersive = findViewById(R.id.rv_immersive)
        rvImmersive.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
        val adapter = ImmersiveItemAdapter(getData())
        rvImmersive.adapter = adapter
        adapter.listener = object : ImmersiveItemAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when(position) {
                    0 -> NormalActivity().intoActivity(this@ImmersiveItemAdapterActivity,null)
                    1 -> MoreFragmentActivity().intoActivity(this@ImmersiveItemAdapterActivity,null)
                    2 -> NormalNavActivity().intoActivity(this@ImmersiveItemAdapterActivity,null)
                    3 -> MoreFragmentlNavActivity().intoActivity(this@ImmersiveItemAdapterActivity,null)
                }
            }

            override fun onItemLongClick(view: View, position: Int) {
            }
        }
    }

    private fun getData(): Array<out String>? {
        return resources?.getStringArray(R.array.immersive_title)
    }

    fun intoActivity(context: Context,bundle: Bundle?) {
        val intent = Intent(context,this::class.java)
        if (bundle == null) {
            context.startActivity(intent)
        }else{
            context.startActivity(intent,bundle)
        }
    }

}
