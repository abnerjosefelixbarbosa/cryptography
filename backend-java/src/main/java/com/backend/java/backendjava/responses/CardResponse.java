package com.backend.java.backendjava.responses;

import com.backend.java.backendjava.entities.Card;
import com.backend.java.backendjava.requests.CardRequest;

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