package com.kevinraupp.service;

import com.kevinraupp.dto.AuthorDTO;
import com.kevinraupp.dto.BookDTO;
import com.kevinraupp.dto.ResultsDTO;
import com.kevinraupp.model.Author;
import com.kevinraupp.model.Book;
import com.kevinraupp.repository.AuthorRepository;
import com.kevinraupp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Service
public class ConstructorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    QuerieAPI querieAPI = new QuerieAPI();
    ConvertData convertData = new ConvertData();
    Scanner scanner = new Scanner(System.in);

    public void saveBook(String url) {
        System.out.println("♦ Buscando....");
        var json = querieAPI.getData(url);
        ResultsDTO resultsDTO = convertData.getData(json, ResultsDTO.class);
        if (resultsDTO.count() != 0) {
            BookDTO bookDTO = resultsDTO.results().getFirst();
            Book book = new Book(bookDTO);
            Author author;
            try {
                var firstAuthor = bookDTO.authors().getFirst();
                AuthorDTO authorDTO = new AuthorDTO(firstAuthor.name(), firstAuthor.yearBirth(), firstAuthor.yearDeath());
                author = new Author(authorDTO);
            } catch (NoSuchElementException e){
                author = new Author("null", 0, 0);
            }
            book.setAuthor(author);
            Book bookSaved = bookRepository.getByTitle(book.getTitle());
            Assistant.titleBook();
            System.out.println(book);
            if(bookSaved == null) {
                Author getAutor = book.getAuthor();
                Author authorAlscannerySaved = authorRepository.findByNameContainingIgnoreCase(getAutor.getName());
                if(authorAlscannerySaved != null) {
                    book.setAuthor(authorAlscannerySaved);
                    authorAlscannerySaved.setBooks(book);
                } else {
                    Author savedAuthor = authorRepository.save(getAutor);
                    book.setAuthor(savedAuthor);
                    savedAuthor.setBooks(book);
                }
                bookRepository.save(book);
                System.out.println("♦ Livro adicionado com sucesso! ♦\n");
            } else {
                System.out.println("♦ Este livro já foi cadastrado! ♦\n");
            }
        } else {
            System.out.println("\n♦ Livro não encontrado! ♦\n");
        }
    }

    public void listBooks() {
        List<Book> books = bookRepository.findAll();
        if(!books.isEmpty()) {
            Assistant.titleBooks();
            books.forEach(System.out::println);
        } else {
            Assistant.message1();
        }
    }

    public void listAuthors() {
        List<Author> authors = authorRepository.findAll();
        if(!authors.isEmpty()) {
            Assistant.titleAuthor();
            authors.forEach(System.out::println);
        } else {
            Assistant.message1();
        }
    }

    public void listAuthorsByYear() {
        try {
            System.out.print("♦ Digite um ano para busca ano: ");
            Integer year = scanner.nextInt();
            scanner.nextLine();
            List<Author> authors = authorRepository.searchAuthorsByYear(year);
            if(!authors.isEmpty()) {
                Assistant.titleBooks();
                authors.forEach(System.out::println);
            } else {
                System.out.println("\n♦ Nenhum autor encontrado pelo ano especificado! ♦\n");
            }
        } catch (InputMismatchException e) {
            Assistant.message4();
            System.out.println("♦ Por favor, digite um ano válido ♦\n");
            scanner.nextLine();
        }
    }

    public void listBooksByLanguage() {
        Assistant.showMenuListLanguage();
        Assistant.message5();
        var choice = scanner.nextLine();
        List<Book> books = bookRepository.findByLanguage(choice);
        if(!books.isEmpty()) {
            Assistant.titleBooks();
            books.forEach(System.out::println);
        } else {
            System.out.println("\n♦ Nenhum resultado no idioma selecionado! ♦\n");
        }
    }

    public void listBooksByTitleOrAuthor() {
        System.out.print("♦ Digite um trecho do título do livrou ou o nome do autor: ");
        var anySearch = scanner.nextLine();
        List<Book> mixedList = bookRepository.findBookByTitleOrAuthorLike(anySearch);
        if(!mixedList.isEmpty()) {
            Assistant.titleBooks();
            mixedList.forEach(System.out::println);
        } else {
            System.out.println("\n♦ Nenhum livro encontrado com o dado informado! ♦\n");
        }
    }

    public void getTop10Donwloads() {
        List<Book> top10downloads = bookRepository.findTop10ByOrderByTotalDownloadsDesc();
        Assistant.topTitle();
        top10downloads.forEach(System.out::println);
    }
}
