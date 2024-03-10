package com.bitcodetech.practicemvvmretrofit.repository

import com.bitcodetech.practicemvvmretrofit.models.User
import com.bitcodetech.practicemvvmretrofit.models.UserResponse
import com.bitcodetech.practicemvvmretrofit.network.UserApiService

class UserRepository(private val userApiService: UserApiService) {

    suspend fun fetchUsers() : ArrayList<User> {
        return userApiService.fetchUsers()
    }

}