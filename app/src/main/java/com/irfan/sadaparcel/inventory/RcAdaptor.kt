package com.irfan.sadaparcel.inventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RcAdaptor<T>(private val itemLayoutManger: ItemLayoutManger) :
    RecyclerView.Adapter<RcAdaptor.AppViewHolder<T>>() {
    private var itemList = ArrayList<T>()
    private var recyclerview: RecyclerView? = null

    fun bindRecyclerView(recyclerview: RecyclerView) {
        recyclerview.adapter = this
    }

    fun setItems(itemList: List<T>) {
        this.itemList.addAll(itemList)
    }

    fun getItem(position: Int): T {
        if (position > itemList.size || position < 0) throw IllegalArgumentException()
        return this.itemList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder<T> {
        val inflater = LayoutInflater.from(parent.context)
        val view  = inflater.inflate(viewType,parent,false)
        return AppViewHolder(view, itemLayoutManger)
    }

    override fun onBindViewHolder(holder: AppViewHolder<T>, position: Int) {
         holder.bind(position,getItem(position))
     }

    override fun getItemViewType(position: Int): Int {
      return  itemLayoutManger.getLayoutId(position)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    class AppViewHolder<in T>(private val view: View, private val itemLayoutManger: ItemLayoutManger) :
        RecyclerView.ViewHolder(view) {
        fun bind(position: Int, item: T) {
            view.tag = Pair(position,item)
            itemLayoutManger.bindView(view)
        }
    }
}

interface ItemLayoutManger {
    fun getLayoutId(position: Int): Int
    fun bindView(view: View)
}