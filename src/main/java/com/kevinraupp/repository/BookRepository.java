package com.kevinraupp.repository;

import com.kevinraupp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Book getByTitle(String title);

    @Query("SELECT b FROM Book b WHERE b.language ILIKE :languageChoice")
    List<Book> findByLanguage(String languageChoice);

    @Query("SELECT b FROM Book b JOIN b.author a WHERE b.title ILIKE %:stringLike% OR a.name ILIKE %:stringLike% ORDER BY a.name")
    List<Book> findBookByTitleOrAuthorLike(String stringLike);

    List<Book> findTop10ByOrderByTotalDownloadsDesc();
}
