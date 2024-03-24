package com.backend.java.backendjava.dtos.responses;

import com.backend.java.backendjava.infra.entities.Card;

public record CardResponse(
        Long id,
        String userDocument,
        String creditCardToken,
        Long cardValue
) {
    public CardResponse(Card card) {
        this(card.getId(), card.getUserDocument(), card.getCreditCardToken(),
                card.getCardValue());
    }
}