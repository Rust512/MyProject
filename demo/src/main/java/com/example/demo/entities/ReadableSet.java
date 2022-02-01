package com.example.demo.entities;

import com.example.demo.entities.readables.Readable;

public interface ReadableSet {
    void add(Readable readable);
    void getFromFile(String path, String delimiter);
    int getSize();
    Readable getByIndex(int index);
    void displayAll();
    void returnReadable(Readable readable);
    void borrowReadable(int index);
}
