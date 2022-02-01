package com.example.demo.entities;

import com.example.demo.entities.readables.Newspaper;
import com.example.demo.entities.readables.Readable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class NewspaperSet implements ReadableSet {
    final private ArrayList<Readable> newspapers;

    public NewspaperSet(){
        newspapers = new ArrayList<>();
    }

    @Override
    public void add(Readable newspaper){
        if(newspapers.contains(newspaper)){
            int index = newspapers.indexOf(newspaper);
            this.newspapers.get(index).increaseStock();
        } else {
            this.newspapers.add(newspaper);
        }
    }

    @Override
    public void getFromFile(String path, String delimiter){
        try(Scanner scanner = new Scanner(Paths.get(path))){
            while(scanner.hasNextLine()){
                String[] row = scanner.nextLine().split(delimiter);
                if(row.length >= 4) {
                    ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo.entities.readables");
                    Newspaper newspaper = (Newspaper) context.getBean("Newspaper");
                    newspaper.setter(row[1], row[2], row[3]);
                    this.add(newspaper);
                } else {
                    System.out.println("Not enough data");
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
        } catch(Exception e){
            System.out.println("There was an error encountered while reading through file : " + e.getMessage());
        }
    }

    public Readable getByIndex(int index){
        try {
            return this.newspapers.get(index);
        }catch(Exception e){
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
    public int getSize(){
        return newspapers.size();
    }

    @Override
    public void returnReadable(Readable newspaper){
        try {
            int index = newspapers.indexOf(newspaper);
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
