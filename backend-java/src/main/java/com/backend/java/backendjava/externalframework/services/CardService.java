package com.backend.java.backendjava.externalframework.services;

import com.backend.java.backendjava.externalframework.entities.Card;
import com.backend.java.backendjava.externalframework.repositories.CardRepository;
import com.backend.java.backendjava.requests.CardRequest;
import com.backend.java.backendjava.responses.CardResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        return cardRepository.save(card);
    }

    public List<Card> readCard() {
        return cardRepository.findAll();
    }

    public Card readCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> {
            throw  new EntityNotFoundException("id not found");
        });
    }

    public Card updateCardById(Long id, Card card) {
        var findById = cardRepository.findById(id).orElseThrow(() -> {
            throw  new EntityNotFoundException("id not found");
        });
        findById.updateCard(card);
        return cardRepository.save(findById);
    }

    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    public void deleteCard() {
        cardRepository.deleteAll();
    }
}
