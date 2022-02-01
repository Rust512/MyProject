package com.example.demo.entities.readables;

import java.util.Random;

public class JustRandom {

    public static double randomDouble(Random generator, double min, double max){
        return (min + generator.nextDouble()*(max - min))*100.00/100.00;
    }
}
