package cn.sayhallo.screenadaption.test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.sayhallo.screenadaption.R

class ScreenAdaptionAdapter(private var data: Array<out String>?) : RecyclerView.Adapter<ScreenAdaptionAdapter.VH>() {

    var listener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val root:View = LayoutInflater.from(parent.context).inflate(R.layout.item_screnn_adaption,parent,false)
        return VH(root)
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tv.text = data!![position]
        holder.itemView.setOnClickListener {
            listener?.onItemClick(it, position)
        }
        holder.itemView.setOnLongClickListener{
            listener?.onItemLongClick(it,position)
            true
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        var tv: TextView = view.findViewById(R.id.item_tools_title)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

}