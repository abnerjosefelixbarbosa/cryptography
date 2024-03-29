package com.backend.java.backendjava.usercases;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.dtos.requests.CardRequest;
import com.backend.java.backendjava.dtos.responses.CardResponse;
import com.backend.java.backendjava.entities.Card;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CardUserCase implements CardAdapter {
    public CardResponse createCard(CardRequest request) {
        validateCard(request);
        return  null;
    }

    public Page<CardResponse> readCard() {
        return null;
    }

    public CardResponse updateCardById(Long id, CardRequest request) {
        validateCard(request);
        return null;
    }

    public void deleteCardById(Long id) {}

    private void  validateCard(CardRequest request) {
        if (request.getCardValue() == 0) {
            throw  new RuntimeException("value card 0");
        }
    }
}
