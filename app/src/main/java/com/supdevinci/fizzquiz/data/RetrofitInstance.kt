package com.supdevinci.fizzquiz.data

import com.supdevinci.fizzquiz.data.api.QuizApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://opentdb.com"

    val api: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quizApi: QuizApi by lazy {
        api.create(QuizApi::class.java)
    }
}