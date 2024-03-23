package com.backend.java.backendjava.adapters;

import com.backend.java.backendjava.domain.entities.Card;

import java.util.List;

public interface CardAdapter {
    Card createCard(Card card);
    List<Card> readCard();
    Card readCardById(Long id);
    Card updateCardById(Long id, Card card);
    void deleteCardById(Long id);
}
