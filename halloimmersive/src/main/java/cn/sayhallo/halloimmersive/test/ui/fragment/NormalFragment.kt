package cn.sayhallo.halloimmersive.test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import cn.sayhallo.halloimmersive.HalloStatusBar
import cn.sayhallo.halloimmersive.R

class NormalFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_normal_test,container,false)
        val tvTitle:TextView = view.findViewById(R.id.tv_title)
        HalloStatusBar.setTopAdater(resources,tvTitle)
        return view
    }
}
