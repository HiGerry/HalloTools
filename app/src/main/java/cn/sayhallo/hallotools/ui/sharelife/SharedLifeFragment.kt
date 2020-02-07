package cn.sayhallo.hallotools.ui.sharelife

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import cn.sayhallo.hallotools.R

class SharedLifeFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
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
            textView.setPadding(0, height, 0, 0)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val resourceId = resources.getIdentifier(
                "status_bar_height",
                "dimen",
                "android"
            )
            val height = resources.getDimensionPixelSize(resourceId)

            textView.setPadding(0, height, 0, 0)
        }
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}