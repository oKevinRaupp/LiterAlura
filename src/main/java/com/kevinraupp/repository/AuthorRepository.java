package com.kevinraupp.repository;

import com.kevinraupp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository  extends JpaRepository<Author, Long> {

    Author findByNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Book b JOIN b.author a WHERE a.yearBirth <= :year and a.yearDeath >= :year")
    List<Author> searchAuthorsByYear(Integer year);
}
