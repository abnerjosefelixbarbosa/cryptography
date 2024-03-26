package com.backend.kotlin.backendkotlin.exceptions

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ExceptionDetails(
    @JsonFormat(pattern = "yyyy-MM-dd HH:ss")
    val localDateTime: LocalDateTime,
    val status: Int,
    val message: String,
    val path: String
)
