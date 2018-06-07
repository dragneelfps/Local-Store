package com.example.srawa.mvpkotlin.adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.srawa.mvpkotlin.R
import com.example.srawa.mvpkotlin.database.employee.Employee
import com.example.srawa.mvpkotlin.util.formatDate
import kotlinx.android.synthetic.main.item_layout_department_employee.view.*

class DepartmentEmployeeListAdapter : BaseAdapter<Employee, DepartmentEmployeeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var empNameTextView: TextView = itemView.emp_name
        var empDOJTextView: TextView = itemView.emp_doj
    }

    override fun getItemCount() = values.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("xyz", "HERRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRE")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_department_employee, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val employee = values[position]
        holder.run {
            empNameTextView.text = employee.name
            empDOJTextView.text = employee.dateOfJoining.formatDate()
        }
    }
}