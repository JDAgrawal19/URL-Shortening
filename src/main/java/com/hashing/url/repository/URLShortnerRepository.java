package com.hashing.url.repository;

import com.hashing.url.model.UrlEntity;
import org.springframework.data.repository.CrudRepository;

public interface URLShortnerRepository extends CrudRepository<UrlEntity, String> {
}
