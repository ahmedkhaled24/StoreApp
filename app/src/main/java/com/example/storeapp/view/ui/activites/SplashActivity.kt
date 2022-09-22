package com.example.storeapp.view.ui.activites

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import com.example.storeapp.R
import com.example.storeapp.view.BaseComponents.BaseActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Execute a task in the background thread after 3 seconds.
        openMainActivity()
    }

    private fun openMainActivity() {
        Executors.newSingleThreadScheduledExecutor().schedule({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2, TimeUnit.SECONDS)
    }
}