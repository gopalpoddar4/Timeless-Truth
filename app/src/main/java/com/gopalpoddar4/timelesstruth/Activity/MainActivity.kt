package com.gopalpoddar4.timelesstruth.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.gopalpoddar4.timelesstruth.API.APIinterfcae
import com.gopalpoddar4.timelesstruth.API.RetrofitHelper
import com.gopalpoddar4.timelesstruth.Adapters.CategoryAdapter
import com.gopalpoddar4.timelesstruth.Adapters.NewsAdapter
import com.gopalpoddar4.timelesstruth.Model.CategoryModel
import com.gopalpoddar4.timelesstruth.VMRepo.NewsRepo
import com.gopalpoddar4.timelesstruth.VMRepo.NewsVMFactory
import com.gopalpoddar4.timelesstruth.VMRepo.NewsViewModel
import com.gopalpoddar4.timelesstruth.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var searchView: SearchView
    lateinit var categoryRcv: RecyclerView
    lateinit var progessBar: LinearProgressIndicator
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: NewsViewModel
    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        searchView = findViewById(R.id.searchView)
        searchView.queryHint = "Search News"

        progessBar=findViewById(R.id.progressBar)
        recyclerView=findViewById(R.id.recyclerView)
        categoryRcv=findViewById(R.id.categoryRcv)

        categoryRcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = FirebaseDatabase.getInstance().getReference("category")

        fetchCategory()


        val api = RetrofitHelper.getAPI().create(APIinterfcae::class.java)
        val repo = NewsRepo(api)
        viewModel = ViewModelProvider(this, NewsVMFactory(repo))[NewsViewModel::class.java]

        SearchNews("news")
        changeProgress(true)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                 SearchNews(query)
                changeProgress(true)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
    private fun fetchCategory() {
        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                 val categoryList = mutableListOf<CategoryModel>()
                for (data in snapshot.children){
                    val categoryModel = data.getValue(CategoryModel::class.java)
                    categoryModel?.let {
                        categoryList.add(it)
                    }
                }
                categoryRcv.adapter = CategoryAdapter(categoryList) { category ->
                    SearchNews(category)
                    changeProgress(true)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }

        })
    }
    private fun SearchNews(query: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                viewModel.getEveryNews(
                    query.toString(),
                    "en",
                    "publishedAt",
                    "b73b3b0c8d244b39a4deb48c53cfb11e"
                )
            } catch (e: Exception) {
                Log.d("api", e.message ?: "Unknow error")
            }
        }

        viewModel.news.observe(this as LifecycleOwner, Observer { artical ->
            try {
                recyclerView.adapter = NewsAdapter(artical, {
                    val intent = Intent(this, NewsActivity::class.java)
                    intent.putExtra("url", it)
                    startActivity(intent)
                })
                changeProgress(false)
            } catch (e: Exception) {
                Toast.makeText(this, "Something Went wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun changeProgress(show: Boolean){
        if (show){
            progessBar.visibility = View.VISIBLE
        }else{
            progessBar.visibility = View.INVISIBLE
        }
    }
}