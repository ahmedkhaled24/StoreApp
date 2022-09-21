package com.example.storeapp.data

import com.example.storeapp.data.remote.RetrofitFactory
import com.example.storeapp.view.viewModel.userViewModel.UserViewModelFactory

object Injection {
    // inject all dependencies will consumed by viewModel
    // return ViewModelFactory instance
    fun provideUserVMFactory(): UserViewModelFactory {
        val retrofit = RetrofitFactory.provideUserApiService()
        return UserViewModelFactory(retrofit)
    }


}