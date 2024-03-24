package com.backend.java.backendjava.infra.repositories;

import com.backend.java.backendjava.infra.entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}
