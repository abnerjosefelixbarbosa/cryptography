package com.backend.kotlin.backendkotlin.usercases

import com.backend.kotlin.backendkotlin.adapters.CardAdapter
import com.backend.kotlin.backendkotlin.dtos.requests.CardRequest
import com.backend.kotlin.backendkotlin.dtos.responses.CardResponse
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component


@Component
class CardUserCase : CardAdapter {
    override fun createCard(request: CardRequest?): CardResponse? {
        validateCard(request)
        return null
    }

    override fun readCard(): Page<CardResponse?>? {
        return null
    }

    override fun updateCardById(id: Long?, request: CardRequest?): CardResponse? {
        validateCard(request)
        return null
    }

    override fun deleteCardById(id: Long?) {}

    private fun validateCard(request: CardRequest?) {
        if (request?.cardValue?.toInt() == 0) {
            throw RuntimeException("value card 0")
        }
    }
}