package com.example.srawa.mvpkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.ui.EmployeeListView
import com.example.srawa.mvpkotlin.util.formatDate
import kotlinx.android.synthetic.main.item_layout_employee.view.*

class EmployeeListAdapter: RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val empNameTextView = itemView.emp_name
        val empIdTextView = itemView.emp_id
        val departmentIdTextView = itemView.dept_name
        val dateOfJoiningTextView = itemView.date_of_joining
    }

    var values = emptyList<EmployeeListView.EmployeeDetail>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_employee, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = values[position].employee
        holder.empNameTextView.text = employee.name
        holder.empIdTextView.text = employee.id.toString()
        holder.departmentIdTextView.text = values[position].deptName
        holder.dateOfJoiningTextView.text = employee.dateOfJoining.formatDate()
    }
}