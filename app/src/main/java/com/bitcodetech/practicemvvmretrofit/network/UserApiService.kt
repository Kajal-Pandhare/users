package com.bitcodetech.practicemvvmretrofit.network

import com.bitcodetech.practicemvvmretrofit.models.User
import com.bitcodetech.practicemvvmretrofit.models.UserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UserApiService {

@GET("users")

suspend fun fetchUsers() : ArrayList<User>

companion object{
    private var userApiService : UserApiService? = null

    fun getInstance() : UserApiService {
        if (userApiService == null){
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            userApiService = retrofit.create(UserApiService::class.java)
        }
        return userApiService!!

    }
}
}