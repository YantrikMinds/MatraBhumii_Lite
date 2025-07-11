package com.example.matrabhumiilite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MarketPriceAdapter(private val prices: List<MarketPrice>) :
    RecyclerView.Adapter<MarketPriceAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageCrop)
        val tvCrop: TextView = view.findViewById(R.id.tvCrop)
        val tvMandi: TextView = view.findViewById(R.id.tvMandi)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvState: TextView = view.findViewById(R.id.tvState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_market_price, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = prices[position]
        holder.tvCrop.text = item.crop
        holder.tvMandi.text = item.mandi
        holder.tvPrice.text = item.price
        holder.tvState.text = item.date

        // Set the image (static for now)
        holder.imageView.setImageResource(R.drawable.img_mandi_price)
    }

    override fun getItemCount() = prices.size
}
