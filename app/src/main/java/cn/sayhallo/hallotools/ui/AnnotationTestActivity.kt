package cn.sayhallo.hallotools.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cn.sayhallo.hallo_annotation.BindView
import cn.sayhallo.hallo_annotation.OnClick
import cn.sayhallo.hallo_butterknife.HalloButterKnife

import cn.sayhallo.hallotools.R

class AnnotationTestActivity : AppCompatActivity() {

    @JvmField
    @BindView(id = R.id.annotation_tv)
    var tv: TextView? = null

    @JvmField
    @BindView(id = R.id.annotation_bt)
    var bt: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_annotation_test)
        HalloButterKnife.bind(this)
        tv!!.text = "我是被修改之后的值"
    }

    @OnClick(ids = [R.id.annotation_tv,R.id.annotation_bt])
    fun click(v: View) {
        when (v.id) {
            R.id.annotation_tv -> {
                tv!!.text = "我是被修改之后的值--自己被点击"
            }
            R.id.annotation_bt -> {
                tv!!.text = "我是被修改之后的值--按钮点击"
            }
        }

    }

    fun intoActivity(context: Context, bundle: Bundle?) {

        val intent = Intent(context, this.javaClass)
        if (bundle == null) {
            context.startActivity(intent)
        } else {
            context.startActivity(intent, bundle)
        }
    }
}
