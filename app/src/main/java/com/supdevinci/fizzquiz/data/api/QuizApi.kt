package com.supdevinci.fizzquiz.data.api

import com.supdevinci.fizzquiz.models.api.CategoriesResponse
import com.supdevinci.fizzquiz.models.api.QuizResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizApi {
    @GET("/api_category.php")
    suspend fun getCategories(): CategoriesResponse

    @GET("/api.php?amount=1&encode=base64&")
    suspend fun getOneQuestionByCategory(@Query("category") id: Int): QuizResponse
}
