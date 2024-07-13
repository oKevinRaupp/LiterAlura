package com.kevinraupp.model;


import com.kevinraupp.dto.AuthorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer yearBirth;
    private Integer yearDeath;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Book> books = new ArrayList<>();

    public Author(){}

    public Author(String name, Integer yearBirth, Integer yearDeath) {}

    public Author(AuthorDTO authorDTO) {
        try {
            String[] author = authorDTO.name().split(",");
            this.name = author[1] + " " + author[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.name = authorDTO.name();
        }
        this.yearBirth = authorDTO.yearBirth();
        this.yearDeath = authorDTO.yearDeath();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearBirth() {
        return yearBirth;
    }
    public void setYearBirth(Integer yearBirth) {
        this.yearBirth = yearBirth;
    }

    public Integer getYearDeath() {
        return yearDeath;
    }
    public void setYearDeath(Integer yearDeath) {
        this.yearDeath = yearDeath;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(Book book) {
        this.books.add(book);
        book.setAuthor(this);
    }

    @Override
    public String toString() {
        return  "Nome: " + name +
                "\nAno de nascimento: " + yearBirth +
                "\nAno de falecimento: " + yearDeath +
                "\nLivros: " + books.stream().map(Book::getTitle).toList() + '\n';
    }
}
