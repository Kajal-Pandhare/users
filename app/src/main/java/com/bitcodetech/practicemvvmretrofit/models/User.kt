package com.bitcodetech.practicemvvmretrofit.models

import com.google.gson.annotations.SerializedName

data class User(
    val login : String,
    val id : String,
    val avatar_url : String,
    val gravatar_id : String,
    val url : String,
    val html_url : String,
    val followers_url : String,
    val following_url : String,

    )
