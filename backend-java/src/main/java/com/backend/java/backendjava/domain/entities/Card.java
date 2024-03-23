package com.backend.java.backendjava.domain.entities;

import com.backend.java.backendjava.requests.CardRequest;
import lombok.*;

import java.io.Serializable;

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
}
