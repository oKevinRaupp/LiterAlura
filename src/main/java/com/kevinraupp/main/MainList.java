package com.kevinraupp.main;

import com.kevinraupp.service.Assistant;
import com.kevinraupp.service.ConstructorService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainList {

    private final ConstructorService constructorService;
    private final Scanner scanner = new Scanner(System.in);

    public MainList(ConstructorService constructorService) {
        this.constructorService = constructorService;
    }

    public void menuList() {
        var option = 0;
        Assistant.showTitle();
        while (option != 99) {
            try {
                Assistant.showMenuList();
                Assistant.message5();
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        constructorService.listBooks();
                        break;
                    case 2:
                        constructorService.listAuthors();
                        break;
                    case 3:
                        constructorService.listAuthorsByYear();
                        break;
                    case 4:
                        constructorService.listBooksByLanguage();
                        break;
                    case 5:
                        constructorService.listBooksByTitleOrAuthor();
                        break;
                    case 6:
                        constructorService.getTop10Donwloads();
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
