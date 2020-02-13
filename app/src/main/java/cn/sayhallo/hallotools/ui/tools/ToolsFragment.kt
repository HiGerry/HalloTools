package cn.sayhallo.hallotools.ui.tools

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.sayhallo.halloimmersive.test.ImmersiveItemAdapterActivity
import cn.sayhallo.hallotools.R
import cn.sayhallo.hallotools.ui.adapter.ToolsAdapter



class ToolsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val rlHomeTitle: RelativeLayout = root.findViewById(R.id.rl_home_title)

        /*
      适配安卓4.4--5.0
       */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val height = resources.getDimensionPixelSize(resourceId)
            rlHomeTitle.setPadding(0, height, 0, 0)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val height = resources.getDimensionPixelSize(resourceId)
            rlHomeTitle.setPadding(0, height, 0, 0)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rvTools: RecyclerView = view.findViewById(R.id.rv_tools)
        rvTools.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        val adapter = ToolsAdapter(getData())
        adapter.listener = object : ToolsAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                /*
                跳转至沉浸式相关界面
                 */
                when(position) {
                    0 -> ImmersiveItemAdapterActivity().intoActivity(context!!,null)
                }
            }

            override fun onItemLongClick(view: View, position: Int) {
                /*
                弹出沉浸式相关的介绍说明
                 */

            }


        }
        rvTools.adapter = adapter
    }

    private fun getData(): Array<out String> {
        return activity!!.resources.getStringArray(R.array.tools_title)
    }

}