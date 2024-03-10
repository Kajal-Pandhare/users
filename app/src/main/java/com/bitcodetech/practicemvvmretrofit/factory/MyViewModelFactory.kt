package com.bitcodetech.practicemvvmretrofit.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.practicemvvmretrofit.repository.UserRepository
import com.bitcodetech.practicemvvmretrofit.viewmodel.UserViewModel

@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(private val userRepository: UserRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(userRepository) as T
    }
}