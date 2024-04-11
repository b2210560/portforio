package com.example.wsp_spring.model;

import java.time.LocalDateTime;

public record pokemonItemValue(String userId, String userName, String pokemonNumber, String pokemonName, String pokemonImage,
                               String posted_at) {
}
