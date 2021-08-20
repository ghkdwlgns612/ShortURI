package com.example.demo.repository;

import com.example.demo.domain.Url;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByhashvalue(String hashvalue);
    boolean existsByhashvalue(String hashvalue);
    boolean existsByname(String name);
    boolean existsByoriginurl(String originurl);
    @Query("SELECT m FROM Url m WHERE m.name= :name and m.originurl= :originurl")
    Optional<Url> findUrl(@Param("name") String name,@Param("originurl") String originurl);
    @Query("SELECT m FROM Url m WHERE m.name= :name")
    List<Url> findAllUrlByName(@Param("name") String name);
}
