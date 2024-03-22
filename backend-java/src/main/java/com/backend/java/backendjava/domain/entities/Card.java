package com.backend.java.backendjava.domain.entities;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private Long id;
    private String userDocument;
    private Long value;
    private String creditCardToken;
}
