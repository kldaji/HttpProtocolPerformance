package com.kldaji.okhttp3retrofit

import okhttp3.ResponseBody
import retrofit2.http.GET

interface SampleService {
    @GET("100/150")
    suspend fun getImage(): ResponseBody
}
