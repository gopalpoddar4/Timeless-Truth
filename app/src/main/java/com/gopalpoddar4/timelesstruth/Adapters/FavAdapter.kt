package com.gopalpoddar4.timelesstruth.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gopalpoddar4.timelesstruth.Adapters.NewsAdapter.VH
import com.gopalpoddar4.timelesstruth.Model.Article
import com.gopalpoddar4.timelesstruth.Model.FavModel
import com.gopalpoddar4.timelesstruth.R

class FavAdapter(private val artical: List<FavModel>, private val onItemClick:(FavModel)-> Unit,private val onLongClick:(Int)-> Unit): RecyclerView.Adapter<FavAdapter.VH>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
         val news = artical[position]
        holder.newsTitle.text = news.newsTitle
        holder.newsSource.text = news.newsSource
        holder.itemView.setOnClickListener {
            onItemClick(news)
        }
        holder.itemView.setOnLongClickListener {
            onLongClick(news.id)

            true
        }


        Glide.with(holder.itemView.context).load(news.imageUrl).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return artical.size
    }


    class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        val newsImage = itemView.findViewById<ImageView>(R.id.news_image)
        val newsTitle = itemView.findViewById<TextView>(R.id.news_title)
        val newsSource = itemView.findViewById<TextView>(R.id.news_source)


    }

}