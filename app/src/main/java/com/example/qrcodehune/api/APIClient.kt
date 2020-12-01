package com.example.qrcodehune.api

import com.example.qrcodehune.api.APIServer.Companion.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    private var gson = GsonBuilder().setLenient().create()
    private val client = OkHttpClient.Builder().build()
    fun getRetro(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}