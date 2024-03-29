package com.backend.java.backendjava.infra.services;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.dtos.requests.CardRequest;
import com.backend.java.backendjava.dtos.responses.CardResponse;
import com.backend.java.backendjava.infra.entities.Card;
import com.backend.java.backendjava.infra.repositories.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CardAdapter cardAdapter;

    public CardResponse createCard(CardRequest request) {
        cardAdapter.createCard(request);
        String userDocument = encoder().encode(request.getUserDocument());
        String creditCardToken = encoder().encode(request.getCreditCardToken());
        Stream<Card> stream = cardRepository.findAll().parallelStream();

        stream.anyMatch((val) -> {
            if (encoder().matches(request.getUserDocument(), val.getUserDocument())) {
                throw new RuntimeException("user document exists");
            }
            if (encoder().matches(request.getCreditCardToken(), val.getCreditCardToken())) {
                throw new RuntimeException("credit card token exists");
            }
            return false;
        });

        Card card = new Card(request);
        card.setUserDocument(userDocument);
        card.setCreditCardToken(creditCardToken);
        card = cardRepository.save(card);
        return new CardResponse(card);
    }

    public Page<CardResponse> readCard(Pageable pageable) {
        return cardRepository.findAll(pageable).map(CardResponse::new);
    }

    public CardResponse readCardById(Long id) {
        Card card = findById(id);
        return new CardResponse(card);
    }

    public CardResponse updateCardById(Long id, CardRequest request) {
        cardAdapter.updateCardById(id, request);
        Card findById = findById(id);
        String userDocument = encoder().encode(request.getUserDocument());
        String creditCardToken = encoder().encode(request.getCreditCardToken());
        Stream<Card> stream = cardRepository.findAll().parallelStream();

        stream.anyMatch((val) -> {
            if (encoder().matches(request.getUserDocument(), val.getUserDocument())) {
                throw new RuntimeException("user document exists");
            }
            if (encoder().matches(request.getCreditCardToken(), val.getCreditCardToken())) {
                throw new RuntimeException("credit card token exists");
            }
            return false;
        });

        Card card = new Card(request);
        findById.updateCard(card);
        findById.setUserDocument(userDocument);
        findById.setCreditCardToken(creditCardToken);
        card = cardRepository.save(findById);
        return new CardResponse(card);
    }

    public void deleteCardById(Long id) {
        findById(id);
        cardRepository.deleteById(id);
    }

    private Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
