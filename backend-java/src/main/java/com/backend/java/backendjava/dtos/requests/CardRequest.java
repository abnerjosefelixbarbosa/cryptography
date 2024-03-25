package com.backend.java.backendjava.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record CardRequest(
        @NotNull(message = "user document null")
        @NotBlank(message = "user document blank")
        String userDocument,
        @NotNull(message = "credit card token null")
        @NotBlank(message = "credit card token blank")
        String creditCardToken,
        @NotNull(message = "card value null")
        Long cardValue
) { }