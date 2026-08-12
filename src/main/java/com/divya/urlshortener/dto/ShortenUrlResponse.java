package com.divya.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ShortenUrlResponse {

    private String shortCode;
    private String shortUrl;
    private String originalUrl;
    private Instant createdAt;
    private Instant expiresAt;
}
