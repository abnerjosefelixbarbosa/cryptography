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
        this.userDocument = request.getUserDocument();
        this.value = request.getCardValue();
        this.creditCardToken = request.getCreditCardToken();
    }
}
