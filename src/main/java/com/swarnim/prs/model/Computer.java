package com.swarnim.prs.model;

import java.util.Random;

public class Computer implements MoveStrategy {
    @Override
    public Move chooseMove() {
        Random random = new Random();
        int choice = random.nextInt(3);
        return Move.values()[choice];
    }

    @Override
    public String getName() {
        return "Computer";
    }
}
