package com.backend.java.backendjava.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CardRequest(
        @NotNull(message = "user document is null")
        @NotBlank(message = "user document is blank")
        String userDocument,
        @NotNull(message = "credit card token is null")
        @NotBlank(message = "credit card token is blank")
        String creditCardToken,
        @NotNull(message = "card value is null")
        Long cardValue
) { }