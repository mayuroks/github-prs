package com.mayur.naviassignment.data

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_BASE_URL = "https://api.github.com/"

private val okHttpClient: OkHttpClient by lazy {
    OkHttpClient.Builder()
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()
}

val loggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}

val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

val gson: Gson by lazy {
    GsonBuilder()
        .setDateFormat(DATE_FORMAT)
        .create()
}

inline fun <reified T> create(): T {
    return retrofit.create(T::class.java)
}

const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"