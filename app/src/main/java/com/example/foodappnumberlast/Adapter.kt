package com.example.foodappnumberlast

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val myList: List<Item>): RecyclerView.Adapter<Adapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = myList[position]

        holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2


        if(position == 0){
            holder.textView1.setBackgroundColor(Color.RED)
        }
    }

    // override fun getItemCount() = myList.size
    override fun getItemCount(): Int {
        return myList.size
    }


    class  ExampleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        // val imageView: ImageView = itemView.image_view ----ez ugyan az elvileg, csak nalam nem megy valamiert
        val textView1: TextView = itemView.findViewById(R.id.text_view_1)
        val textView2: TextView = itemView.findViewById(R.id.text_view_2)
    }
}