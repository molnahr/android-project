package com.example.restaurantapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.model.Restaurant
import kotlinx.android.synthetic.main.row_item.view.*


class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Restaurant>()

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item,parent,false))
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        holder.itemView.text_view_name.text= myList[position].name
        holder.itemView.text_view_price.text = myList[position].price.toString()
        holder.itemView.text_view_addres.text = myList[position].address
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    fun setData(newList: List<Restaurant>){
        myList = newList
        notifyDataSetChanged()
    }
}