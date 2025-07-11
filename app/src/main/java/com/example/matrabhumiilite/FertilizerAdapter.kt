package com.example.matrabhumiilite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FertilizerAdapter(private val list: List<Pair<String, String>>) :
    RecyclerView.Adapter<FertilizerAdapter.FertilizerViewHolder>() {

    class FertilizerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCrop: TextView = view.findViewById(R.id.tvCrop)
        val tvRecommendation: TextView = view.findViewById(R.id.tvRecommendation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FertilizerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fertilizer, parent, false)
        return FertilizerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FertilizerViewHolder, position: Int) {
        val (crop, recommendation) = list[position]
        holder.tvCrop.text = crop
        holder.tvRecommendation.text = recommendation
    }

    override fun getItemCount(): Int = list.size
}
