package com.backend.java.backendjava.domain.usercases;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.domain.entities.Card;
import com.backend.java.backendjava.responses.CardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Component
public class CardUserCase implements CardAdapter {
    public Card createCard(Card card) {
        validateCard(card);
        return  null;
    }

    public List<Card> readCard() {
        return null;
    }

    public Card readCardById(Long id) {
        return null;
    }

    public Card updateCardById(Long id, Card card) {
        validateCard(card);
        return null;
    }

    public void deleteCardById(Long id) {
    }

    private void  validateCard(Card card) {
        if (card.getValue() == 0) {
            throw  new RuntimeException("value card is 0");
        }
    }
}
