package com.backend.kotlin.backendkotlin.controllers

import com.backend.kotlin.backendkotlin.dtos.requests.CardRequest
import com.backend.kotlin.backendkotlin.dtos.responses.CardResponse
import com.backend.kotlin.backendkotlin.infra.services.CardService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/cards")
class CardController {
    @Autowired
    private lateinit var cardService: CardService

    @PostMapping
    fun createCard(@RequestBody  @Valid request: CardRequest): ResponseEntity<CardResponse> {
        val response = cardService.createCard(request)
        return ResponseEntity.status(201).body(response)
    }

    @GetMapping
    fun readCard(pageable: Pageable): ResponseEntity<Page<CardResponse?>> {
        val responses = cardService.readCard(pageable)
        return ResponseEntity.status(200).body(responses)
    }

    @GetMapping("/{id}")
    fun readCardById(id: Long): ResponseEntity<CardResponse> {
        val response = cardService.readCardById(id)
        return ResponseEntity.status(200).body(response)
    }

    @PutMapping("/{id}")
    fun updateCardById(
        @PathVariable id: Long,
        @RequestBody  @Valid request: CardRequest
    ): ResponseEntity<CardResponse> {
        val response = cardService.updateCardById(id, request)
        return ResponseEntity.status(200).body(response)
    }

    @DeleteMapping("/{id}")
    fun deleteCardById(@PathVariable id: Long): ResponseEntity<Void> {
        cardService.deleteCardById(id)
        return ResponseEntity.status(204).body(null)
    }
}