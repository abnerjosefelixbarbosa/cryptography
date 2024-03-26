package com.backend.kotlin.backendkotlin.adapters

import com.backend.kotlin.backendkotlin.dtos.requests.CardRequest
import com.backend.kotlin.backendkotlin.dtos.responses.CardResponse
import org.springframework.data.domain.Page


interface CardAdapter {
    fun createCard(request: CardRequest?): CardResponse?
    fun readCard(): Page<CardResponse?>?
    fun updateCardById(id: Long?, request: CardRequest?): CardResponse?
    fun deleteCardById(id: Long?)
}