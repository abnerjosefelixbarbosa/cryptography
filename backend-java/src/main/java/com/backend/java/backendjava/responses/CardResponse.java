package com.backend.java.backendjava.responses;

public record CardResponse(
        Long id,
        String userDocument,
        String creditCardToken,
        Long cardValue
) { }