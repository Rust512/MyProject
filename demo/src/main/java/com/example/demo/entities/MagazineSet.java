package com.example.demo.entities;

import com.example.demo.entities.readables.Magazine;
import com.example.demo.entities.readables.Readable;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class MagazineSet implements ReadableSet {
    final private ArrayList<Readable> magazines;

    public MagazineSet(){
        magazines = new ArrayList<>();
    }

    @Override
    public void getFromFile(String path, String delimiter){
        try(Scanner scanner = new Scanner(Paths.get(path))){
            while(scanner.hasNextLine()){
                String[] row = scanner.nextLine().split(delimiter);
                if(row.length >= 2){
                    Magazine magazine = new Magazine();
                    magazine.setter(row[0], row[1]);
                    this.add(magazine);
                } else {
                    System.out.println("Not enough data");
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        } catch(Exception e){
            System.out.println("There was an error encountered while reading through file : " + e.getMessage());
        }
    }

    @Override
    public void add(Readable magazine){
        if(magazines.contains(magazine)){
            int index = this.magazines.indexOf(magazine);
            magazines.get(index).increaseStock();
        } else {
            magazines.add(magazine);
        }
    }

    @Override
    public Readable getByIndex(int index){
        try{
            return magazines.get(index);
        } catch(Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public int getSize(){
        return magazines.size();
    }

    @Override
    public void displayAll(){
        for(int i = 0; i < this.getSize(); i++){
            System.out.println((i + 1) + " : " + this.getByIndex(i));
        }
    }

    @Override
    public void returnReadable(Readable magazine){
        try {
            int index = magazines.indexOf(magazine);
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
