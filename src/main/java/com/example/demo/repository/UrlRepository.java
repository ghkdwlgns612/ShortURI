package com.example.demo.repository;

import com.example.demo.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByhashvalue(String hashvalue);
    boolean existsByhashvalue(String hashvalue);
    boolean existsByname(String name);
    boolean existsByoriginurl(String originurl);
}
