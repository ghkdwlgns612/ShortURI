package com.example.demo.repository;

import com.example.demo.entity.Uri;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UriRepository extends JpaRepository<Uri,Long> {
    Optional<Uri> findBychangeduri(String changeduri);
}
