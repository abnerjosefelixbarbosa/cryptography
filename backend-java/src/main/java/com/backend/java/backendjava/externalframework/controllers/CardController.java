package com.backend.java.backendjava.externalframework.controllers;

import com.backend.java.backendjava.requests.CardRequest;
import com.backend.java.backendjava.responses.CardResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CardController {
    @PostMapping
    public ResponseEntity<CardResponse> createCard(@RequestBody @Valid CardRequest request) {
        return ResponseEntity.status(201).body(null);
    }

    @GetMapping
    public ResponseEntity<List<CardResponse>> readCard() {
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> readCardById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponse> updateCardById(@PathVariable Long id, @RequestBody @Valid CardRequest request) {
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCard() {
        return ResponseEntity.status(200).body(null);
    }
}
