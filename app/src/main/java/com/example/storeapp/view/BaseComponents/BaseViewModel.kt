package com.example.storeapp.view.BaseComponents

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storeapp.data.Injection
import com.example.storeapp.view.viewModel.userViewModel.UserViewModel


open class BaseViewModel(activity: FragmentActivity): ViewModel(){

    private val userViewModelFactory= Injection.provideUserVMFactory()
    val mUserViewModel = ViewModelProvider(activity, userViewModelFactory)[UserViewModel::class.java]

    fun showToast(context: Context, message:String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}