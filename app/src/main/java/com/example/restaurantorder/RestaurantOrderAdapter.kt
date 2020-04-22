package com.example.restaurantorder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RestaurantOrderAdapter ( private var list: MutableList <Order>, private val clickListener: (String) -> Unit)
    : RecyclerView.Adapter<OrderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.activity_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
    }

    fun updateOrder (order: MutableList<Order>){
        list.clear()
        list = order
        notifyDataSetChanged()

    }
}
