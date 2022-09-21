package com.example.storeapp

import android.os.Bundle
import com.example.storeapp.view.BaseComponents.BaseActivity
import com.example.storeapp.view.ui.fragments.HomeFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showToast("Welcome")

        supportFragmentManager.beginTransaction().replace(R.id.fragment, HomeFragment()).commit()
    }
}