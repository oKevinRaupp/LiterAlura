package com.kevinraupp.main;

import com.kevinraupp.service.Assistant;
import com.kevinraupp.service.ConstructorService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainSearch {

    private final ConstructorService constructorService;
    private final Scanner scanner = new Scanner(System.in);

    public MainSearch(ConstructorService constructorService) {
        this.constructorService = constructorService;
    }

    public void menuSearch() {
        var option = 0;
        Assistant.showTitle();
        while (option != 99) {
            try {
                Assistant.showMenuSearch();
                Assistant.message5();
                option = scanner.nextInt();
                scanner.nextLine();
                String url = "http://gutendex.com/books/?search=";
                switch (option) {
                    case 1:
                        var nameBook = Assistant.takeBookName();
                        var endApi = url + nameBook;
                        constructorService.saveBook(endApi);
                        break;
                    case 2:
                        var nameAuthor = Assistant.takeAuthorName();
                        var bookName = Assistant.takeBookName();
                        var endAPI = url + nameAuthor + "%20" + bookName;
                        constructorService.saveBook(endAPI);
                        break;
                    case 9:
                        Main main = new Main(constructorService);
                        main.menu();
                        break;
                    case 0:
                        Assistant.closingSentence();
                        System.exit(0);
                        break;
                    default:
                        Assistant.message2();
                        Assistant.message3();
                }
            } catch (InputMismatchException e) {
                Assistant.message4();
                Assistant.message3();
                scanner.nextLine();
            }
        }
    }
}
