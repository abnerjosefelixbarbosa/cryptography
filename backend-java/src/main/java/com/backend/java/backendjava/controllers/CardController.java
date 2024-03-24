package com.backend.java.backendjava.controllers;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.entities.Card;
import com.backend.java.backendjava.dtos.requests.CardRequest;
import com.backend.java.backendjava.dtos.responses.CardResponse;
import com.backend.java.backendjava.infra.services.CardService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardRequest request) {
        var response = cardService.createCard(new com.backend.java.backendjava.infra.entities.Card(request));
        return ResponseEntity.status(201).body(new CardResponse(response));
    }

    @GetMapping
    public ResponseEntity<Page<CardResponse>> readCard(Pageable pageable) {
        var responses = cardService.readCard(pageable);
        return ResponseEntity.status(200).body(responses.map(CardResponse::new));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> updateCardById(@PathVariable Long id, @RequestBody @Valid CardRequest request) {
        var response = cardService.updateCardById(id, new com.backend.java.backendjava.infra.entities.Card(request));
        return ResponseEntity.status(200).body(new CardResponse(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardById(@PathVariable Long id) {
        cardService.deleteCardById(id);
        return ResponseEntity.status(204).body(null);
    }
}
