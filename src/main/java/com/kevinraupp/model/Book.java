package com.kevinraupp.model;

import com.kevinraupp.dto.BookDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "Livros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String language;
    private Integer totalDownloads;

    @ManyToOne
    private Author author;

    public Book() {}

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.title();
        this.language = bookDTO.language().getFirst();
        this.totalDownloads = bookDTO.numberDownload();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getTotalDownloads() {
        return totalDownloads;
    }
    public void setTotalDownloads(Integer totalDownloads) {
        this.totalDownloads = totalDownloads;
    }

    public Author getAuthor() {
        return author;
    }
    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return  "Titulo: " + title +
                "\nAutor: " + author.getName() +
                "\nIdioma: " + language +
                "\nTotal de Downloads: " + totalDownloads + '\n';
    }
}
