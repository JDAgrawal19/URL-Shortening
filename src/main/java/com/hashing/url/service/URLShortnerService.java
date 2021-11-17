package com.hashing.url.service;

import com.google.common.hash.Hashing;
import com.hashing.url.model.UrlEntity;
import com.hashing.url.repository.URLShortnerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class URLShortnerService {

    private final URLShortnerRepository urlShortnerRepository;
    private final KafkaProducerService kafkaProducerService;

    public URLShortnerService(URLShortnerRepository urlShortnerRepository, KafkaProducerService kafkaProducerService) {
        this.urlShortnerRepository = urlShortnerRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public String createShortenedURL(String URL){
        if(StringUtils.isNotEmpty(URL)){
            UrlEntity urlEntity = new UrlEntity();
            String hashedUrl = encodeUrl(URL);
            urlEntity.setOriginalUrl(URL);
            urlEntity.setHashUrl(hashedUrl);
            LocalDateTime createdAt = LocalDateTime.now();
            LocalDateTime expiredAt = createdAt.plusDays(1);
            urlEntity.setCreatedAt(createdAt);
            urlEntity.setExpiredAt(expiredAt);
            urlShortnerRepository.save(urlEntity);
            return hashedUrl;
        }
        return null;
    }

    public String getOriginalURL(String hash){
        UrlEntity urlEntity = urlShortnerRepository.findById(hash).get();
        if(urlEntity.getExpiredAt().isBefore(LocalDateTime.now()) || urlEntity.getClickCount() >=10)
            throw new RuntimeException("Link is Expired, Please Generate Again");
        urlEntity.setClickCount(urlEntity.getClickCount()+1);
        urlShortnerRepository.save(urlEntity);
        kafkaProducerService.addMessageToTopic(urlEntity.getOriginalUrl());
        return urlEntity.getHashUrl();
    }

    private String encodeUrl(String url)
    {
        String encodedUrl = "";
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.murmur3_32()
                .hashString(url.concat(time.toString()), StandardCharsets.UTF_8)
                .toString();
        return  encodedUrl;
    }
}
