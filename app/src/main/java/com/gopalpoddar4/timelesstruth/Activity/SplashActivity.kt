package com.gopalpoddar4.timelesstruth.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.gopalpoddar4.timelesstruth.R
import kotlinx.coroutines.Runnable

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        window.statusBarColor = ContextCompat.getColor(this, R.color.main_color)

        Handler().postDelayed(Runnable() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2500)
    }
}