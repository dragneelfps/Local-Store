package com.nooblabs.example.localstore.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.nooblabs.example.localstore.R
import com.nooblabs.example.localstore.database.product.Product
import kotlinx.android.synthetic.main.item_layout_product.view.*

class ProductListAdapter : BaseAdapter<Product, ProductListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productNameTextView: TextView = itemView.product_name
        var productTypeTextView: TextView = itemView.product_type
        var productIdTextView: TextView = itemView.product_id
        var productQuantityTextView: TextView = itemView.product_quantity
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout_product, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = values[position]
        holder.run {
            productNameTextView.text = product.name
            productTypeTextView.text = product.type.toString()
            productIdTextView.text = product.id.toString()
            productQuantityTextView.text = product.quantity.toString()
        }

    }
}