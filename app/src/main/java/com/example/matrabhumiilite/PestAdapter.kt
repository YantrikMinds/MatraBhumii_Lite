package com.example.matrabhumiilite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PestAdapter(private val pestList: List<Pest>) :
    RecyclerView.Adapter<PestAdapter.PestViewHolder>() {

    class PestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pestImage: ImageView = itemView.findViewById(R.id.pestImage)
        val titleText: TextView = itemView.findViewById(R.id.titleText)
        val cropText: TextView = itemView.findViewById(R.id.cropText)
        val controlText: TextView = itemView.findViewById(R.id.controlText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pest, parent, false)
        return PestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PestViewHolder, position: Int) {
        val item = pestList[position]
        holder.titleText.text = item.title
        holder.cropText.text = "प्रभावित फसलें: ${item.cropsAffected}"
        holder.controlText.text = "नियंत्रण उपाय: ${item.controlMeasures}"
        holder.pestImage.setImageResource(R.drawable.img_pest) // image in drawable
    }

    override fun getItemCount(): Int = pestList.size
}


