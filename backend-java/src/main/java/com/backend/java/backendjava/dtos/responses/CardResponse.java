package com.backend.java.backendjava.dtos.responses;

import com.backend.java.backendjava.infra.entities.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponse {
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long cardValue;

    public CardResponse(Card card) {
        this.id = card.getId();
        this.userDocument = card.getUserDocument();
        this.creditCardToken = card.getCreditCardToken();
        this.cardValue = card.getCardValue();
    }
}