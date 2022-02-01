package com.example.demo;

import com.example.demo.entities.readables.Readable;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    Library library;

    public UserInterface(Scanner scanner, Library library){
        this.scanner = scanner;
        this.library = library;
    }

    public void start(){
        library.open();
        whatToDo();
    }

    private void whatToDo(){
        System.out.println("What do you want to do? Select option:\n" +
                "1. Borrow?\n" +
                "2. Return?\n" +
                "3. Exit?");
        int input = Integer.parseInt(scanner.nextLine());
        if(input == 1){
            this.WhatToBorrow();
        } else if(input == 2){
            this.WhatToReturn();
        } else {
            System.out.println("Exiting...");
            this.close();
        }
    }

    private void WhatToBorrow(){
        System.out.println("What do you want to borrow? Choose from the options below:\n" +
                "1. book.\n" +
                "2. newspaper\n" +
                "3. magazine\n" +
                "4. back");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                this.borrowing("book");
                break;
            case 2:
                this.borrowing("newspaper");
                break;
            case 3:
                this.borrowing("magazine");
                break;
            case 4:
                this.whatToDo();
                break;
            default:
                System.out.println("Exiting out of the the interface due to wrong input...");
        }
    }

    private void borrowing(String readable) {
        System.out.println("Please choose one of the following by entering a valid index...:");
        int index;
        switch (readable){
            case "book":
                this.library.displayAllBooks();
                System.out.println("Please enter a valid index:");
                index = Integer.parseInt(scanner.nextLine());
                if(this.confirm(this.library.getBook(index - 1), "borrow")){
                    this.library.getBook(index - 1).borrowToRead();
                    System.out.println("Thank you for your patronage!!!\nPlease come again later...");
                } else {
                    System.out.println("returning to the home page:");
                }
                this.whatToDo();
                break;
            case "newspaper":
                this.library.displayAllNewspapers();
                System.out.println("Please enter a valid index:");
                index = Integer.parseInt(scanner.nextLine());
                if(this.confirm(this.library.getNewspaper(index - 1), "borrow")){
                    this.library.getNewspaper(index - 1).borrowToRead();
                    System.out.println("Thank you for your patronage!!!\nPlease come again later...");
                } else {
                    System.out.println("returning to the home page:");
                }
                this.whatToDo();

                break;
            case "magazine":
                this.library.displayAllMagazines();
                System.out.println("Please enter a valid index:");
                index = Integer.parseInt(scanner.nextLine());
                if(this.confirm(this.library.getMagazine(index - 1), "borrow")){
                    this.library.getMagazine(index - 1).borrowToRead();
                    System.out.println("Thank you for your patronage!!!\nPlease come again later...");
                } else {
                    System.out.println("returning to the home page:");
                }
                this.whatToDo();
                break;
        }
    }

    private boolean confirm(Readable readable, String action) {
        System.out.println("Are you sure you want to " + action + " this?");
        System.out.println(readable);
        System.out.println("(y/n)?");
        String answer = scanner.nextLine();
        return (answer.equals("y"));
    }

    private void WhatToReturn(){
        System.out.println("What do you want to return? Choose from the options below:\n" +
                "1. book.\n" +
                "2. newspaper\n" +
                "3. magazine\n" +
                "4. back");
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                this.returning("book");
                break;
            case 2:
                this.returning("newspaper");
                break;
            case 3:
                this.returning("magazine");
                break;
            case 4:
                this.whatToDo();
                break;
            default:
                System.out.println("Exiting out of the the interface due to wrong input...");
        }
    }

    private void returning(String readable) {
        System.out.println("Please choose one of the following by entering a valid index...:");
        int index;
        switch (readable){
            case "book":
                this.library.displayAllBooks();
                System.out.println("Please enter a valid index:");
                index = Integer.parseInt(scanner.nextLine());
                if(this.confirm(this.library.getBook(index - 1), "return")){
                    this.library.getBook(index - 1).returnBorrowed();
                    System.out.println("Thank you for your patronage!!!\nPlease come again later...");
                } else {
                    System.out.println("returning to the home page:");
                }
                this.whatToDo();
                break;
            case "newspaper":
                this.library.displayAllNewspapers();
                System.out.println("Please enter a valid index:");
                index = Integer.parseInt(scanner.nextLine());
                if(this.confirm(this.library.getNewspaper(index - 1), "return")){
                    this.library.getNewspaper(index - 1).returnBorrowed();
                    System.out.println("Thank you for your patronage!!!\nPlease come again later...");
                } else {
                    System.out.println("returning to the home page:");
                }
                this.whatToDo();

                break;
            case "magazine":
                this.library.displayAllMagazines();
                System.out.println("Please enter a valid index:");
                index = Integer.parseInt(scanner.nextLine());
                if(this.confirm(this.library.getMagazine(index - 1), "return")){
                    this.library.getMagazine(index - 1).returnBorrowed();
                    System.out.println("Thank you for your patronage!!!\nPlease come again later...");
                } else {
                    System.out.println("returning to the home page:");
                }
                this.whatToDo();
                break;
        }
    }

    private void close(){
        this.scanner.close();
    }

}