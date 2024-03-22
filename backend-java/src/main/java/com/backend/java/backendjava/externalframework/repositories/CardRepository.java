package com.backend.java.backendjava.externalframework.repositories;

import com.backend.java.backendjava.externalframework.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
