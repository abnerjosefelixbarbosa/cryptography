package com.backend.java.backendjava.services;

import com.backend.java.backendjava.entities.Card;
import com.backend.java.backendjava.repositories.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card createCard(Card card) {
        var save = cardRepository.save(card);
        save.setCreditCardToken(crypt(save.getCreditCardToken()));
        save.setUserDocument(crypt(save.getUserDocument()));
        return save;
    }

    public Page<Card> readCard(Pageable pageable) {
        var findAll = cardRepository.findAll(pageable);
        findAll.map((val) -> {
            val.setUserDocument(crypt(val.getUserDocument()));
            val.setCreditCardToken(crypt(val.getCreditCardToken()));
            return val;
        });
        return findAll;
    }

    public Card readCardById(Long id) {
        var findById = cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
        findById.setCreditCardToken(crypt(findById.getCreditCardToken()));
        findById.setUserDocument(crypt(findById.getUserDocument()));
        return findById;
    }

    public Card updateCardById(Long id, Card card) {
        var readCardById = readCardById(id);
        readCardById.updateCard(card);
        var save = cardRepository.save(readCardById);
        save.setCreditCardToken(crypt(save.getCreditCardToken()));
        save.setUserDocument(crypt(save.getUserDocument()));
        return save;
    }

    public void deleteCardById(Long id) {
        readCardById(id);
        cardRepository.deleteById(id);
    }

    public void deleteCard() {
        cardRepository.deleteAll();
    }

    private String crypt(String value) {
        try {
            var algorithm = MessageDigest.getInstance("SHA-512");
            var messageDigest = algorithm.digest(value.getBytes());
            var hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", 0xFF & b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
