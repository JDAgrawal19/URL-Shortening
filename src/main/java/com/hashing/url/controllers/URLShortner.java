package com.hashing.url.controllers;

import com.hashing.url.service.URLShortnerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class URLShortner {

    private final URLShortnerService urlShortnerService;

    public URLShortner(URLShortnerService urlShortnerService) {
        this.urlShortnerService = urlShortnerService;
    }

    @PostMapping("/hasher")
    public String getHashedUrl(@RequestParam("url")String url){
        return urlShortnerService.getShortenedURL(url);
    }
}
