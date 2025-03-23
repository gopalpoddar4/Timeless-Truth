package com.gopalpoddar4.timelesstruth.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gopalpoddar4.timelesstruth.Adapters.FavAdapter
import com.gopalpoddar4.timelesstruth.NewsApplication
import com.gopalpoddar4.timelesstruth.R
import com.gopalpoddar4.timelesstruth.VMRepo.NewsVMFactory
import com.gopalpoddar4.timelesstruth.VMRepo.NewsViewModel

class FavrioutActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: NewsViewModel
    lateinit var favAdapter: FavAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favriout)

        val back = findViewById<ImageView>(R.id.favBack)
        recyclerView = findViewById(R.id.favRcv)
        back.setOnClickListener {
            onBackPressed()
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        val repo = (application as NewsApplication).repo

        viewModel = ViewModelProvider(this, NewsVMFactory(repo))[NewsViewModel::class.java]

        viewModel.FetchFavNews()

        viewModel.fav.observe(this, Observer{
            favAdapter= FavAdapter(it,{
                val intent = Intent(this, NewsActivity::class.java)
                intent.putExtra("url",it.newsUrl)
                startActivity(intent)
            },{
                viewModel.DeleteFavNews(it)
                favAdapter.notifyItemRemoved(it)
                Toast.makeText(this,"Removed from favourite", Toast.LENGTH_SHORT).show()
                viewModel.FetchFavNews()
                favAdapter.notifyDataSetChanged()
            })
            recyclerView.adapter = favAdapter
        })

    }

}