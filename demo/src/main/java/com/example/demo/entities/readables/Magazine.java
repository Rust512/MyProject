package com.example.demo.entities.readables;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

@Component
public class Magazine implements Readable {
    String title;
    String language;
    int stock;
    double price;

    public Magazine(){
        Random gen = new Random();
        this.stock = (int) Math.floor(JustRandom.randomDouble(gen, 5.0, 8.0));
        this.price = JustRandom.randomDouble(gen, 50.0, 250.0);
    }

    public void setter(String title, String language){
        this.title = title;
        this.language = language;
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
            System.out.println("The magazine you are trying to borrow is out of stock...");
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
        return "Magazine{" +
                "title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Magazine magazine = (Magazine) o;
        return stock == magazine.stock && title.equals(magazine.title) && language.equals(magazine.language);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, language, stock);
    }
}
