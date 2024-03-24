package com.backend.java.backendjava.infra.entities;

import com.backend.java.backendjava.dtos.requests.CardRequest;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_tb")
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String userDocument;
    @Column
    private String creditCardToken;
    @Column
    private Long cardValue;

    public Card(CardRequest request) {
        this.userDocument = request.userDocument();
        this.creditCardToken = request.creditCardToken();
        this.cardValue = request.cardValue();
    }

    public void updateCard(Card card) {
        userDocument = card.getUserDocument();
        creditCardToken = card.getCreditCardToken();
        cardValue = card.getCardValue();
    }
}
