package com.backend.java.backendjava.dtos.requests;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class CardRequest {
        @NotNull(message = "user document null")
        @NotBlank(message = "user document blank")
        private String userDocument;
        @NotNull(message = "credit card token null")
        @NotBlank(message = "credit card token blank")
        private String creditCardToken;
        @NotNull(message = "card value null")
        private Long cardValue;
}