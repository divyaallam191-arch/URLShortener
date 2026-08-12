package com.divya.urlshortener.service;

import com.divya.urlshortener.entity.ShortUrl;
import com.divya.urlshortener.repository.ShortUrlRepository;
import com.divya.urlshortener.util.ShortCodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class UrlShortenerService {

    private static final int MAX_COLLISION_ATTEMPTS = 10;

    private final ShortUrlRepository shortUrlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public UrlShortenerService(ShortUrlRepository shortUrlRepository, ShortCodeGenerator shortCodeGenerator) {
        this.shortUrlRepository = shortUrlRepository;
        this.shortCodeGenerator = shortCodeGenerator;
    }

    public ShortUrl createShortUrl(String originalUrl) {
        return shortUrlRepository.findByOriginalUrl(originalUrl)
                .orElseGet(() -> {
                    ShortUrl shortUrl = new ShortUrl();
                    shortUrl.setShortCode(generateUniqueShortCode());
                    shortUrl.setOriginalUrl(originalUrl);
                    return shortUrlRepository.save(shortUrl);
                });
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortCode) {
        return shortUrlRepository.findByShortCode(shortCode)
                .map(ShortUrl::getOriginalUrl)
                .orElseThrow(() -> new NoSuchElementException("No URL found for short code: " + shortCode));
    }

    private String generateUniqueShortCode() {
        for (int attempt = 0; attempt < MAX_COLLISION_ATTEMPTS; attempt++) {
            String code = shortCodeGenerator.generate();
            if (shortUrlRepository.findByShortCode(code).isEmpty()) {
                return code;
            }
        }
        throw new IllegalStateException("Failed to generate a unique short code after " + MAX_COLLISION_ATTEMPTS + " attempts");
    }
}
