package com.example.demo.entities.readables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

@Component
public class Book implements Readable {
    public String title;
    public String language;
    @Autowired
    public Person author;
    final private double price;
    private int stock;

    public Book(){
        Random gen = new Random();
        this.price = JustRandom.randomDouble(gen, 50.0, 500.0);
        this.stock = (int) Math.floor(JustRandom.randomDouble(gen, 5.0, 8.0));
    }

    public void setter(String title, String language, Person author){
        this.title = title;
        this.language = language;
        this.author = author;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    public String getAuthor() {
        return author.getName();
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isAvailable() {
        return stock > 0;
    }

    @Override
    public void borrowToRead() {
        if(this.isAvailable()){
            stock--;
        } else {
            System.out.println("The book you are trying to borrow is out of stock...");
        }
    }

    @Override
    public void returnBorrowed() {
        stock++;
    }

    @Override
    public void increaseStock(){
        stock++;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", author=" + author +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) && language.equals(book.language) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, language, author);
    }
}
