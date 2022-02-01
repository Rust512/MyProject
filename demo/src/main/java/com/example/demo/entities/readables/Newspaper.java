package com.example.demo.entities.readables;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

@Component
public class Newspaper implements Readable {
    public String title;
    public String language;
    public String origin;
    int stock;
    double price;

    public Newspaper(){
        Random gen = new Random();
        this.price = JustRandom.randomDouble(gen, 5.0, 15.0);
        this.stock = (int) Math.floor(JustRandom.randomDouble(gen, 3.0, 5.0));
    }

    public void setter(String title, String language, String origin){
        this.title = title;
        this.language = language;
        this.origin = origin;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getLanguage() {
        return language;
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
        if(this.isAvailable()) {
            stock--;
        } else {
            System.out.println("The newspaper you are trying to borrow is out of stock...");
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
        return "Newspaper{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", origin='" + origin + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Newspaper newspaper = (Newspaper) o;
        return title.equals(newspaper.title) && language.equals(newspaper.language) && origin.equals(newspaper.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, language, origin);
    }
}
