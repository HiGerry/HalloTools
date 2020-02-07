package cn.sayhallo.hallotools.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.sayhallo.hallotools.R

class ToolsAdapter(val dataList: Array<out String>) : RecyclerView.Adapter<ToolsAdapter.VH>() {

    var listener : OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_tools,parent,false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tv.text = dataList[position]
        holder.itemView.setOnClickListener {
            listener?.onItemClick(it, position)
        }
        holder.itemView.setOnLongClickListener{
            listener?.onItemLongClick(it,position)
            true
        }
    }

    public fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        var tv:TextView = view.findViewById(R.id.item_tools_title)
    }

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

}
