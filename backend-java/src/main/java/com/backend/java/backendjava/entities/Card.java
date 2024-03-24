package com.backend.java.backendjava.entities;

import com.backend.java.backendjava.dtos.requests.CardRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private String userDocument;
    private Long value;
    private String creditCardToken;

    public Card(CardRequest request) {
        this.userDocument = request.userDocument();
        this.value = request.cardValue();
        this.creditCardToken = request.creditCardToken();
    }

    public Card(com.backend.java.backendjava.infra.entities.Card card) {
        this.userDocument = card.getUserDocument();
        this.value = card.getCardValue();
        this.creditCardToken = card.getCreditCardToken();
    }
}
