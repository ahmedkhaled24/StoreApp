package com.example.storeapp.view.BaseComponents

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


open class BaseActivity: AppCompatActivity() {

    fun showToast(message: String){
        Toast.makeText(this.applicationContext, message, Toast.LENGTH_SHORT).show()
    }

//    override fun getBaseContext(): Context {
//        return LocalizationUtil.applyLanguageContext(super.getBaseContext(), Locale(SharedPre.getCheckOnLanguage(this)))
//    }
//
//    override fun getApplicationContext(): Context {
//        return LocalizationUtil.applyLanguageContext(super.getApplicationContext(), Locale(SharedPre.getCheckOnLanguage(this)))
//    }
}