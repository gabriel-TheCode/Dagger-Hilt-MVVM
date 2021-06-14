package com.thecode.dagger_hilt_mvvm.retrofit

import retrofit2.http.GET

interface BlogApi {

    @GET("blogs")
    suspend fun get(): List<BlogObjectResponse>
}