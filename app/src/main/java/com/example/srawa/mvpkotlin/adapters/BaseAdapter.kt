package com.example.srawa.mvpkotlin.adapters

import android.support.v7.widget.RecyclerView

abstract class BaseAdapter<T, V : RecyclerView.ViewHolder?> : RecyclerView.Adapter<V>() {

    open var values = emptyList<T>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = values.size
}