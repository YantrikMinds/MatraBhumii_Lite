package com.example.matrabhumiilite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KrishiFaqAdapter(private val faqList: List<Pair<String, String>>) :
    RecyclerView.Adapter<KrishiFaqAdapter.FaqViewHolder>() {

    class FaqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQ: TextView = itemView.findViewById(R.id.tvQ)
        val tvA: TextView = itemView.findViewById(R.id.tvA)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaqViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_krishi_faq, parent, false)
        return FaqViewHolder(view)
    }

    override fun onBindViewHolder(holder: FaqViewHolder, position: Int) {
        val (question, answer) = faqList[position]
        holder.tvQ.text = question
        holder.tvA.text = answer
    }

    override fun getItemCount() = faqList.size
}

