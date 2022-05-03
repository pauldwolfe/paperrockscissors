package com.github.bartjohn;

public class Main {

    public static void main(String[] args) {
        PaperRockScissors game = new PaperRockScissors(new ComputerPlayer(new RandomStrategy()));
        CommandLineInterface cli = new CommandLineInterface(game);
        cli.start();
    }
}
