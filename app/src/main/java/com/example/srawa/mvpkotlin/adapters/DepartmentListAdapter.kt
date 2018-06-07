package com.example.srawa.mvpkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.database.department.Department
import kotlinx.android.synthetic.main.item_layout_department.view.*

class DepartmentListAdapter : BaseAdapter<Department, DepartmentListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var departmentNameTextView: TextView = itemView.dept_name
        var departmentIdTextView: TextView = itemView.dept_id
    }

    private var onClickListener: ClickListener? = null

    fun setOnClickListener(listener: ClickListener?) {
        onClickListener = listener
    }

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_department, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val department = values[position]
        holder.run {
            departmentNameTextView.text = department.name
            departmentIdTextView.text = department.id.toString()
            itemView.setOnClickListener {
                onClickListener?.onClick(department.id)
            }
        }
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.setOnClickListener(null)
    }

    interface ClickListener {
        fun onClick(deptId: Long)
    }
}