package com.backend.java.backendjava.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CardRequest(
        @NotNull
        @NotBlank
        String userDocument,
        @NotNull
        @NotBlank
        String creditCardToken,
        @NotNull
        Long cardValue
) { }