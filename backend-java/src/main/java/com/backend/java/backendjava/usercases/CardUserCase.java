package com.backend.java.backendjava.usercases;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardUserCase implements CardAdapter {
    public Card createCard(Card card) {
        validateCard(card);
        return  null;
    }

    public Page<Card> readCard() {
        return null;
    }

    public Card updateCardById(Long id, Card card) {
        validateCard(card);
        return null;
    }

    public void deleteCardById(Long id) {}

    private void  validateCard(Card card) {
        if (card.getValue() == 0) {
            throw  new RuntimeException("value card is 0");
        }
    }
}
