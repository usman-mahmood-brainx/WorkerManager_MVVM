package com.example.mvvm_retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query
import com.example.mvvm_retrofit.models.Result


class QuotePagingAdapter(private val quotes:List<Result>) : RecyclerView.Adapter<QuotePagingAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote = itemView.findViewById<TextView>(R.id.tvQuote)

    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = quotes[position]
        holder.quote.text = "${position+1} :  ${item.content}"


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote_layout, parent, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quotes.size
    }



}