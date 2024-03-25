package com.backend.java.backendjava.adapters;

import com.backend.java.backendjava.dtos.requests.CardRequest;
import com.backend.java.backendjava.dtos.responses.CardResponse;
import com.backend.java.backendjava.entities.Card;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CardAdapter {
    CardResponse createCard(CardRequest request);
    Page<CardResponse> readCard();
    CardResponse updateCardById(Long id, CardRequest request);
    void deleteCardById(Long id);
}
