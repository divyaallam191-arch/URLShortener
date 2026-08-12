package com.divya.urlshortener.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class ShortenUrlRequest {

    @NotBlank
    @URL
    private String url;
}
