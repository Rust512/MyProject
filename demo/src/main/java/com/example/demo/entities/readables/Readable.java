package com.example.demo.entities.readables;

public interface Readable {
    String getTitle();
    String getLanguage();
    double getPrice();
    boolean isAvailable();
    void borrowToRead();
    void returnBorrowed();
    void increaseStock();
}
