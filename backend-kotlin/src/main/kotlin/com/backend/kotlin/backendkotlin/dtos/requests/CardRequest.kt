package com.backend.kotlin.backendkotlin.dtos.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CardRequest(
    @NotNull(message = "user document null")
    @NotBlank(message = "user document blank")
    val userDocument: String,
    @NotNull(message = "credit card token null")
    @NotBlank(message = "credit card token blank")
    val creditCardToken: String,
    @NotNull(message = "card value null")
    val cardValue: Long
)
