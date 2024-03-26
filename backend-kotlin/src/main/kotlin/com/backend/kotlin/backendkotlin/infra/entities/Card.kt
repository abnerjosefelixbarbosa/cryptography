package com.backend.kotlin.backendkotlin.infra.entities

import com.backend.kotlin.backendkotlin.dtos.requests.CardRequest
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "card_tb")
data class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column(nullable = false, unique = true)
    var userDocument: String,
    @Column(nullable = false, unique = true)
    var creditCardToken: String,
    @Column(nullable = false)
    var cardValue: Long
) {
    constructor(request: CardRequest) : this(null, request.userDocument, request.creditCardToken, request.cardValue)

    fun updateCard(card: Card) {
        userDocument = card.userDocument
        creditCardToken = card.creditCardToken
        cardValue = card.cardValue
    }
}
