package com.gopalpoddar4.timelesstruth.Activity

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gopalpoddar4.timelesstruth.R

class NewsActivity : AppCompatActivity() {
    lateinit var webView: WebView
    lateinit var favroiteBtn: FloatingActionButton
    lateinit var back: ImageView
    lateinit var info: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news)

        val url = intent.getStringExtra("url")

        webView = findViewById(R.id.webView)
        back = findViewById(R.id.back)
        info = findViewById(R.id.info)

        back.setOnClickListener {
            onBackPressed()
        }
        info.setOnClickListener {
            infoDialog()
        }
        webView.settings.apply {
            javaScriptEnabled=true
            domStorageEnabled=true
            cacheMode= WebSettings.LOAD_DEFAULT
        }
        webView.webViewClient= WebViewClient()

        webView.loadUrl(url.toString())
    }

    override fun onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack()
        }else{
            super.onBackPressed()
        }

    }
    private fun infoDialog(){
        val view = LayoutInflater.from(this).inflate(R.layout.info_dailog,null)
        val builder = AlertDialog.Builder(this, com.google.android.material.R.style.ThemeOverlay_Material3_Dialog)
        builder.setView(view)
        builder.setCancelable(true)
        val dailog = builder.create()

        dailog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dailog.window?.addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND)
        dailog.show()
    }
}