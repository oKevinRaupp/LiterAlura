package com.kevinraupp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AuthorDTO(String name,
                        @JsonAlias("birth_year") Integer yearBirth,
                        @JsonAlias("death_year") Integer yearDeath) {
}
