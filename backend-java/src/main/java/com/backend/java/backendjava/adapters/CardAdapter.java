package com.backend.java.backendjava.adapters;

import com.backend.java.backendjava.domain.entities.Card;

public interface CardAdapter {
    Card createCard(Card card);
    Card readCard();
    Card updateCard(Long id, Card card);
    void deleteCard(Long id);
}
