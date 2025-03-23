package com.gopalpoddar4.timelesstruth.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gopalpoddar4.timelesstruth.Model.CategoryModel
import com.gopalpoddar4.timelesstruth.R

class CategoryAdapter(private val categoryList: List<CategoryModel>,private val onClick:(String)-> Unit):
    RecyclerView.Adapter<CategoryAdapter.VH>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(
        holder: VH,
        position: Int
    ) {
        val categoryItem: CategoryModel = categoryList[position]
        holder.categorytxt.text = categoryItem.categoryName
        holder.itemView.setOnClickListener {
            onClick(categoryItem.categotyCode)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView){
        val categorytxt = itemView.findViewById<TextView>(R.id.category)
    }
}