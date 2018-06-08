package com.nooblabs.example.localstore.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nooblabs.example.localstore.R
import com.nooblabs.example.localstore.ui.employee.EmployeeListView
import com.nooblabs.example.localstore.util.formatDate
import kotlinx.android.synthetic.main.item_layout_employee.view.*

class EmployeeListAdapter : BaseAdapter<EmployeeListView.EmployeeDetail, EmployeeListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val empNameTextView = itemView.emp_name
        val empIdTextView = itemView.emp_id
        val departmentIdTextView = itemView.dept_name
        val dateOfJoiningTextView = itemView.date_of_joining
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_employee, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = values[position].employee
        holder.run {
            empNameTextView.text = employee.name
            empIdTextView.text = employee.id.toString()
            departmentIdTextView.text = values[position].deptName
            dateOfJoiningTextView.text = employee.dateOfJoining.formatDate()
        }

    }
}