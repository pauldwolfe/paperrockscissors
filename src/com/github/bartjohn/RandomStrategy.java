package com.github.bartjohn;

import java.util.Random;

public class RandomStrategy implements Strategy {
    Random rand;

    public RandomStrategy() {
        rand = new Random();
    }

    public int execute() {
        return rand.nextInt(3);
    }
}
