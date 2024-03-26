package com.backend.kotlin.backendkotlin.dtos.responses

import com.backend.kotlin.backendkotlin.infra.entities.Card

data class CardResponse(
    var id: Long?,
    var userDocument: String,
    var creditCardToken: String,
    var cardValue: Long
) {
    constructor(card: Card) : this(card.id, card.userDocument, card.creditCardToken, card.cardValue)
}
