package com.backend.kotlin.backendkotlin.infra.services

import com.backend.kotlin.backendkotlin.adapters.CardAdapter
import com.backend.kotlin.backendkotlin.dtos.requests.CardRequest
import com.backend.kotlin.backendkotlin.dtos.responses.CardResponse
import com.backend.kotlin.backendkotlin.infra.entities.Card
import com.backend.kotlin.backendkotlin.infra.repositories.CardRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Stream

@Service
class CardService {
    @Autowired
    private lateinit var cardRepository: CardRepository
    @Autowired
    private lateinit var cardAdapter: CardAdapter

    fun createCard(request: CardRequest): CardResponse {
        cardAdapter.createCard(request)
        val userDocument = encoder().encode(request.userDocument)
        val creditCardToken = encoder().encode(request.creditCardToken)
        val stream: Stream<Card?> = cardRepository.findAll().parallelStream()

        stream.anyMatch {
            if (encoder().matches(request.userDocument, it?.userDocument)) {
                throw RuntimeException("user document exists")
            }
            if (encoder().matches(request.creditCardToken, it?.creditCardToken)) {
                throw RuntimeException("credit card token exists")
            }
            false
        }

        var card: Card = Card(request)
        card.userDocument = (userDocument)
        card.creditCardToken = (creditCardToken)
        card = cardRepository.save(card)
        return CardResponse(card)
    }

    fun readCard(pageable: Pageable): Page<CardResponse?> {
        val findAll = cardRepository.findAll(pageable);
        return findAll.map { card -> CardResponse(card!!) }
    }

    fun readCardById(id: Long): CardResponse {
        val card: Card = findById(id)!!
        return CardResponse(card)
    }

    fun updateCardById(id: Long, request: CardRequest): CardResponse {
        cardAdapter.updateCardById(id, request)
        val findById: Card = findById(id)!!
        val userDocument = encoder().encode(request.userDocument)
        val creditCardToken = encoder().encode(request.creditCardToken)
        val stream: Stream<Card?> = cardRepository.findAll().parallelStream()

        stream.anyMatch {
            if (encoder().matches(request.userDocument, it?.userDocument)) {
                throw RuntimeException("user document exists")
            }
            if (encoder().matches(request.creditCardToken, it?.creditCardToken)) {
                throw RuntimeException("credit card token exists")
            }
            false
        }

        var card: Card = Card(request)
        findById.updateCard(card)
        findById.userDocument = (userDocument)
        findById.creditCardToken = (creditCardToken)
        card = cardRepository.save(findById)
        return CardResponse(card)
    }

    fun deleteCardById(id: Long) {
        findById(id)
        cardRepository.deleteById(id)
    }

    private fun findById(id: Long): Card? {
        val findById = cardRepository.findById(id).orElseThrow {
            EntityNotFoundException("id not found")
        }
        return findById
    }

    private fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}