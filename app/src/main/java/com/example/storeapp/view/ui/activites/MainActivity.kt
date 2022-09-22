package com.example.storeapp.view.ui.activites

import android.os.Bundle
import com.example.storeapp.R
import com.example.storeapp.view.BaseComponents.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Navigation components it works automatically
        //will open home fragment (products)
    }
}