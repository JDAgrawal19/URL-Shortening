package com.hashing.url.service;

import com.hashing.url.model.UrlEntity;
import com.hashing.url.repository.URLShortnerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class URLShortnerService {

    private final URLShortnerRepository urlShortnerRepository;

    public URLShortnerService(URLShortnerRepository urlShortnerRepository) {
        this.urlShortnerRepository = urlShortnerRepository;
    }

    public String getShortenedURL(String URL){
        UrlEntity urlEntity = new UrlEntity();
        String hashedUrl = Base64.getUrlEncoder().encodeToString(URL.getBytes());
        urlEntity.setOriginalUrl(URL);
        urlEntity.setHashUrl(hashedUrl);
        urlEntity.setCreatedAt(LocalDateTime.now());
        urlShortnerRepository.save(urlEntity);
        return hashedUrl;
    }
}
