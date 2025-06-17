package com.supdevinci.fizzquiz.data.api

import com.supdevinci.fizzquiz.models.api.ApiResponse
import retrofit2.http.GET

interface QuizApi {
    @GET("/api.php?amount=1")
    suspend fun getOneQuestion(): ApiResponse
}
