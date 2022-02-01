package com.example.demo.entities;

import com.example.demo.entities.readables.Book;
import com.example.demo.entities.readables.Person;
import com.example.demo.entities.readables.Readable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class BookSet implements ReadableSet {
    final private ArrayList<Readable> books;

    public BookSet(){
        books = new ArrayList<>();
    }

    @Override
    public void add(Readable book){
        if(this.books.contains(book)){
            int index = this.books.indexOf(book);
            this.books.get(index).increaseStock();
        } else {
            this.books.add(book);
        }
    }

    @Override
    public void getFromFile(String path, String delimiter){
        try(Scanner scanner = new Scanner(Paths.get(path))){
            while(scanner.hasNextLine()){
                String[] row = scanner.nextLine().split(delimiter);
                if(row.length < 2){
                    throw new ArrayIndexOutOfBoundsException();
                } else {
                    ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo.entities.readables");
                    Book book = (Book) context.getBean("Book");
                    book.setter(row[0], "English", new Person(row[1]));
                    this.add(book);
                }
            }
        } catch(Exception e){
            System.out.println("There was an error encountered while reading through file : " + e.getMessage());
        }
    }

    @Override
    public int getSize(){
        return this.books.size();
    }

    @Override
    public Readable getByIndex(int index){
        try{
            return this.books.get(index);
        } catch(Exception e){
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void displayAll(){
        for(int i = 1; i <= this.getSize(); i++){
            System.out.println(i + " : " + this.getByIndex(i - 1));
        }
    }

    @Override
    public void returnReadable(Readable book){
        try {
            int index = books.indexOf(book);
            this.getByIndex(index).returnBorrowed();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void borrowReadable(int index) throws ArrayIndexOutOfBoundsException, NullPointerException{
        this.getByIndex(index).borrowToRead();
    }
}
