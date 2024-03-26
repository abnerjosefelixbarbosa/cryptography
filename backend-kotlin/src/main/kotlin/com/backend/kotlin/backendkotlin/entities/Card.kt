package com.backend.kotlin.backendkotlin.entities
data class Card(
    val id: Long,
    val userDocument: String,
    val value: Long,
    val creditCardToken: String
)
