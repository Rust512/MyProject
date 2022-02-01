package com.example.demo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        UserInterface user = new UserInterface(scanner, library);
        user.start();
    }
}
