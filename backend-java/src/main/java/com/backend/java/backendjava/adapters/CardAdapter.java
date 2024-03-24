package com.backend.java.backendjava.adapters;

import com.backend.java.backendjava.entities.Card;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CardAdapter {
    Card createCard(Card card);
    Page<Card> readCard();
    Card updateCardById(Long id, Card card);
    void deleteCardById(Long id);
}
