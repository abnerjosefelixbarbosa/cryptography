package com.backend.java.backendjava.infra.services;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.dtos.requests.CardRequest;
import com.backend.java.backendjava.dtos.responses.CardResponse;
import com.backend.java.backendjava.infra.entities.Card;
import com.backend.java.backendjava.infra.repositories.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardAdapter cardAdapter;
    @Autowired
    private EncryptionService encryptionService;

    public CardResponse createCard(CardRequest request) {
        cardAdapter.createCard(request);
        var hashUserDocument = encryptionService.encript(request.userDocument());
        var hashCreditCardToken = encryptionService.encript(request.creditCardToken());
        var stream = cardRepository.findAll().stream();

        /*
        stream.anyMatch((val) -> {
            if (encryptionService.compareEncript(request.userDocument(), hashUserDocument)) {
                throw new RuntimeException("user document equal");
            }
            return false;
        });
        */

        var card = new Card(request);
        card.setUserDocument(hashUserDocument);
        card.setCreditCardToken(hashCreditCardToken);
        card = cardRepository.save(card);
        return new CardResponse(card);
    }

    public Page<CardResponse> readCard(Pageable pageable) {
        return cardRepository.findAll(pageable).map(CardResponse::new);
    }

    public CardResponse updateCardById(Long id, CardRequest request) {
        cardAdapter.updateCardById(id, request);
        var findById = findById(id);
        var hashUserDocument = encryptionService.encript(findById.getUserDocument());
        var hashCreditCardToken = encryptionService.encript(findById.getCreditCardToken());
        var stream = cardRepository.findAll().stream();

        stream.anyMatch((val) -> {
            if (encryptionService.compareEncript(request.userDocument(), hashUserDocument)) {
                throw new RuntimeException("user document equal");
            }
            return false;
        });

        var card = new Card(request);
        findById.updateCard(card);
        findById.setUserDocument(hashUserDocument);
        findById.setCreditCardToken(hashCreditCardToken);

        return new CardResponse(card);
    }

    public void deleteCardById(Long id) {
        findById(id);
        cardRepository.deleteById(id);
    }

    private Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
    }
}
