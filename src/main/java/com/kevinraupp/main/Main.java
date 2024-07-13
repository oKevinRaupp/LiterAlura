package com.kevinraupp.main;

import com.kevinraupp.service.Assistant;
import com.kevinraupp.service.ConstructorService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private final ConstructorService constructorService;
    private final Scanner scanner = new Scanner(System.in);

    public Main(ConstructorService constructorService) {
        this.constructorService = constructorService;
    }

    public void menu() {
        var option = 0;
        Assistant.showTitle();
        while (option != 99) {
            try {
                Assistant.showMenuOptions();
                Assistant.message5();
                option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        MainSearch mainSearch = new MainSearch(constructorService);
                        mainSearch.menuSearch();
                        break;
                    case 2:
                        MainList mainList = new MainList(constructorService);
                        mainList.menuList();
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
