package com.github.bartjohn;

import java.util.Scanner;

public class CommandLineInterface {
    private PaperRockScissors game;

    public CommandLineInterface(PaperRockScissors game) {
        this.game = game;
    }

    public void start() {
        System.out.println("Welcome to Paper Rock Scissors!");
        Scanner cli = new Scanner(System.in);
        boolean done = false;

        while (!done) {
            System.out.println("Please enter a number from 1 to 4 to make a selection:");
            System.out.println("1. Paper\n2. Rock\n3. Scissors\n4. Exit game");
            String input = cli.nextLine();
            switch (input) {
                case "1":
                case "2":
                case "3":
                    try {
                        game.play(Integer.parseInt(input) - 1);
                    } catch (Exception e) {
                        System.out.printf("Exiting game due to unexpected exception: %s\n", e.getMessage());
                        done = true;
                    }
                    break;
                case "4":
                    game.exit();
                    done = true;
                    break;
                default:
                    System.out.printf("Unrecognized input \"%s\"\n", input);
            }
        }
        cli.close();
    }
}
