package com.supdevinci.fizzquiz.models.api

import kotlinx.serialization.SerialName

data class ApiResponse(
    @SerialName("response_code")
    val responseCode: Int,
    val results: List<Question>
)