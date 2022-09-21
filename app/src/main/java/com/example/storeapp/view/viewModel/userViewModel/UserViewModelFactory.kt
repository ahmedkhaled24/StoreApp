package com.example.storeapp.view.viewModel.userViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storeapp.model.ApiService

class UserViewModelFactory(private val userApiService: ApiService) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userApiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}