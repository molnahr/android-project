package com.example.foodappnumberlast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ez mondja meg hany elemet generaljunk kiautomatikusan.--ahogy gorgetunk lefele jelennek meg
        val exampleList = generatieList(50)

        val recyclew_view = view.findViewById<RecyclerView>(R.id.recycle_view)
        recyclew_view.adapter = Adapter(exampleList)
        val layoutManager = LinearLayoutManager(context)
        recyclew_view.layoutManager = layoutManager
        recyclew_view.setHasFixedSize(true)
    }
    private fun generatieList(size: Int): List<Item> {
        val list = ArrayList<Item>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_baseline_fastfood_24
                1 -> R.drawable.ic_baseline_fastfood_24
                else -> R.drawable.ic_launcher_foreground
            }
            val item = Item(drawable, "Item $i", "Item 2")
            list += item
        }
        return list
    }
}