package com.backend.java.backendjava.externalframework.entities;

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

    public void updateCard(Card card) {
        id = card.getId();
        userDocument = card.getUserDocument();
        creditCardToken = card.getCreditCardToken();
        cardValue = card.getCardValue();
    }
}
