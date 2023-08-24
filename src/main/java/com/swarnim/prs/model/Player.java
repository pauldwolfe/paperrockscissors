package com.swarnim.prs.model;

import java.util.Scanner;

public class Player implements MoveStrategy {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move chooseMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.print(name + ", enter your move (paper, rock, or scissors) (Type quit to exit): ");
        String input = scanner.nextLine().toUpperCase();

        try {
            return Move.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input. Please enter rock, paper, or scissors. Type quit to exit.");
            return chooseMove();
        }
    }
}
