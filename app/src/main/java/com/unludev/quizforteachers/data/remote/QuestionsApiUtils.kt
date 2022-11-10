package com.unludev.quizforteachers.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val  BASE_URL = "https://raw.githubusercontent.com/unludev/a/main/"

object QuestionsApiUtils {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

    val questionApiservice: QuestionsApiService by lazy { retrofit.create(QuestionsApiService::class.java)}
}