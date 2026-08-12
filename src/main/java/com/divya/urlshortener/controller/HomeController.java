package com.divya.urlshortener.controller;

import com.divya.urlshortener.dto.ShortenUrlRequest;
import com.divya.urlshortener.dto.ShortenUrlResponse;
import com.divya.urlshortener.entity.ShortUrl;
import com.divya.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.NoSuchElementException;

@Controller
public class HomeController {

    private final UrlShortenerService urlShortenerService;

    public HomeController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("request", new ShortenUrlRequest());
        return "index";
    }

    @PostMapping("/shorten")
    public String shorten(@Valid @ModelAttribute("request") ShortenUrlRequest request,
                          BindingResult bindingResult,
                          Model model,
                          HttpServletRequest httpRequest) {
        if (bindingResult.hasErrors()) {
            return "index";
        }

        ShortUrl shortUrl = urlShortenerService.createShortUrl(request.getUrl());
        model.addAttribute("result", toResponse(shortUrl, httpRequest));
        return "index";
    }

    @GetMapping("/{shortCode}")
    public String redirect(@PathVariable String shortCode) {
        try {
            String originalUrl = urlShortenerService.getOriginalUrl(shortCode);
            return "redirect:" + originalUrl;
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Short URL not found");
        }
    }

    private ShortenUrlResponse toResponse(ShortUrl shortUrl, HttpServletRequest httpRequest) {
        String shortLink = ServletUriComponentsBuilder.fromRequestUri(httpRequest)
                .replacePath("/" + shortUrl.getShortCode())
                .build()
                .toUriString();

        ShortenUrlResponse response = new ShortenUrlResponse();
        response.setShortCode(shortUrl.getShortCode());
        response.setShortUrl(shortLink);
        response.setOriginalUrl(shortUrl.getOriginalUrl());
        response.setCreatedAt(shortUrl.getCreatedAt());
        response.setExpiresAt(shortUrl.getExpiresAt());
        return response;
    }
}
