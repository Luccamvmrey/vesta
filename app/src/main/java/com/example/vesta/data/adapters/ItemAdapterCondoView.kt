package com.example.vesta.data.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.vesta.R
import com.example.vesta.data.condo.models.Tower

class ItemAdapterCondoView(private val context: Context, private val towers: ArrayList<Tower>,
                           private var onItemClicked: (tower: Tower) -> Unit):
    RecyclerView.Adapter<ItemAdapterCondoView.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardView: CardView
        val tvTowerName: TextView
        val tvTowerStories: TextView
//        val tvVisitedStories: TextView

        init {
            cardView = view.findViewById(R.id.item_tower_cv)
            tvTowerName = view.findViewById(R.id.item_tower_name_tv)
            tvTowerStories = view.findViewById(R.id.item_tower_stories_tv)
//            tvVisitedStories = view.findViewById(R.id.item_tower_visited_tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_custom_row_tower,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tower = towers[position]

        if (tower.name == null) {
            holder.tvTowerName.text = String.format("Torre %d", position + 1)
        }

    }

    override fun getItemCount(): Int {
        return towers.size
    }
}