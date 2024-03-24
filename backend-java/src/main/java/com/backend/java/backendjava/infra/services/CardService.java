package com.backend.java.backendjava.infra.services;

import com.backend.java.backendjava.adapters.CardAdapter;
import com.backend.java.backendjava.infra.entities.Card;
import com.backend.java.backendjava.infra.repositories.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final CardAdapter cardAdapter;

    public CardService(CardRepository cardRepository, CardAdapter cardAdapter) {
        this.cardRepository = cardRepository;
        this.cardAdapter = cardAdapter;
    }

    public Card createCard(Card card) {
        cardAdapter.createCard(new com.backend.java.backendjava.entities.Card(card));
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

    public Card updateCardById(Long id, Card card) {
        cardAdapter.updateCardById(id, new com.backend.java.backendjava.entities.Card(card));
        var findById = findById(id);
        findById.updateCard(card);
        var save = cardRepository.save(findById);
        save.setCreditCardToken(crypt(save.getCreditCardToken()));
        save.setUserDocument(crypt(save.getUserDocument()));
        return save;
    }

    public void deleteCardById(Long id) {
        findById(id);
        cardRepository.deleteById(id);
    }

    private Card findById(Long id) {
        var findById = cardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
        return  findById;
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
