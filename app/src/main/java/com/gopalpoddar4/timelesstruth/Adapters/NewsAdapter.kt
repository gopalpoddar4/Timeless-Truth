package com.gopalpoddar4.timelesstruth.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gopalpoddar4.timelesstruth.Model.Article
import com.gopalpoddar4.timelesstruth.R

class NewsAdapter(private val artical: List<Article>, private val onItemClick:(String)-> Unit): RecyclerView.Adapter<NewsAdapter.VH>() {
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
        val news: Article = artical[position]
        holder.newsTitle.text = news.title
        holder.newsSource.text = news.source.name
        holder.itemView.setOnClickListener {
             onItemClick(news.url)
        }
        Glide.with(holder.itemView.context).load(news.urlToImage).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return artical.size
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        val newsImage = itemView.findViewById<ImageView>(R.id.news_image)
        val newsTitle = itemView.findViewById<TextView>(R.id.news_title)
        val newsSource = itemView.findViewById<TextView>(R.id.news_source)

         fun showimage(){

        }
    }
}