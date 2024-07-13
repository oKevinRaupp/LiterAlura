package com.kevinraupp.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(String title,
                      List<AuthorDTO> authors,
                      @JsonAlias("languages") List<String> language,
                      @JsonAlias("download_count") Integer numberDownload) {
}
