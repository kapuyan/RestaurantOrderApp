package com.example.restaurantorder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_order.view.*
import kotlinx.android.synthetic.main.item.view.*

class OrderViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    fun bind (order: Order, clickListener: (String) -> Unit){
        itemView.nameTxt.text=order.client
        itemView.setOnClickListener{order.client?.let {it1 -> clickListener(it1)}}
        itemView.dishTxt.text=order.dish
        itemView.setOnClickListener{order.dish?.let {it1 -> clickListener(it1)}}
    }


}