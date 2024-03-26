package com.backend.kotlin.backendkotlin.infra.repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.backend.kotlin.backendkotlin.infra.entities.Card

@Repository
interface CardRepository : JpaRepository<Card?, Long?>