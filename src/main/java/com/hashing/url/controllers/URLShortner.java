package com.hashing.url.controllers;

import com.hashing.url.service.URLShortnerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class URLShortner {

    private final URLShortnerService urlShortnerService;

    public URLShortner(URLShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    @PostMapping("/hasher")
    public String getHashedUrl(@RequestParam("url")String url){
        return urlShortnerService.createShortenedURL(url);
    }

    @GetMapping("/{hashed_url}")
    public String getOriginalUrl(@PathVariable String hashed_url){
        return urlShortnerService.getOriginalURL(hashed_url);
    }
}
