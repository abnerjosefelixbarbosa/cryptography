package com.backend.java.backendjava.controllers;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.entities.Card;
import com.backend.java.backendjava.dtos.requests.CardRequest;
import com.backend.java.backendjava.dtos.responses.CardResponse;
import com.backend.java.backendjava.infra.services.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardRequest request) {
        CardResponse response = cardService.createCard(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<CardResponse>> readCard(Pageable pageable) {
        Page<CardResponse> responses = cardService.readCard(pageable);
        return ResponseEntity.status(200).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> readCardById(Long id) {
        CardResponse response = cardService.readCardById(id);
        return ResponseEntity.status(200).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> updateCardById(@PathVariable Long id, @RequestBody @Valid CardRequest request) {
        CardResponse response = cardService.updateCardById(id, request);
        return ResponseEntity.status(200).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardById(@PathVariable Long id) {
        cardService.deleteCardById(id);
        return ResponseEntity.status(204).body(null);
    }
}
