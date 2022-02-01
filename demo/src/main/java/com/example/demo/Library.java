package com.example.demo;

import com.example.demo.entities.*;
import com.example.demo.entities.readables.Readable;
import org.jetbrains.annotations.NotNull;

public class Library {
    final private ReadableSet books, papers, trends;

    public Library() {
        books = new BookSet();
        papers = new NewspaperSet();
        trends = new MagazineSet();
    }

    public void open(){
        books.getFromFile("src/main/java/com/example/demo/entities/data/BooksAndAuthors.txt", "\t");
        papers.getFromFile("src/main/java/com/example/demo/entities/data/Newspapers.txt", "\t");
        trends.getFromFile("src/main/java/com/example/demo/entities/data/Magazines.txt", ",");
    }

    public void displayAllBooks(){
        System.out.println("-------------------------------------BOOKS---------------------------------------");
        books.displayAll();
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void displayAllNewspapers(){
        System.out.println("----------------------------------NEWSPAPERS-------------------------------------");
        papers.displayAll();
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void displayAllMagazines(){
        System.out.println("-----------------------------------MAGAZINES-------------------------------------");
        trends.displayAll();
        System.out.println("---------------------------------------------------------------------------------");
    }

    public void borrow(@NotNull String readableType, int index) throws ArrayIndexOutOfBoundsException{
        switch(readableType){
            case "book":
                books.borrowReadable(index);
                break;
            case "magazine":
                trends.borrowReadable(index);
                break;
            case "newspaper":
                papers.borrowReadable(index);
                break;
            default:
                System.out.println(readableType + " type of material is not available to read in this Library!");
                break;
        }
    }

    public void returnBorrowed(@NotNull String readableType, int index) throws ArrayIndexOutOfBoundsException{
        switch(readableType){
            case "book":
                books.getByIndex(index).returnBorrowed();
                break;
            case "magazine":
                trends.getByIndex(index).returnBorrowed();
                break;
            case "newspaper":
                papers.getByIndex(index).returnBorrowed();
                break;
            default:
                System.out.println(readableType + " type of material is not available to read in this Library!");
                break;
        }
    }

    public int getNumberOfBooks(){
        return this.books.getSize();
    }

    public int getNumberOfNewspapers(){
        return this.papers.getSize();
    }

    public int getNumberOfMagazines(){
        return this.trends.getSize();
    }

    public Readable getBook(int index){
        return this.books.getByIndex(index);
    }

    public Readable getNewspaper(int index){
        return this.papers.getByIndex(index);
    }

    public Readable getMagazine(int index){
        return this.trends.getByIndex(index);
    }

}
