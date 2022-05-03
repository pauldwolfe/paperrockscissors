package com.github.bartjohn;

public class GameHistory {
    private int gamesWon = 0;
    private int gamesLost = 0;
    private int ties = 0;

    public void recordOutcome(PaperRockScissors.Outcome outcome) {
        switch (outcome) {
            case USER:
                gamesWon++;
                break;
            case COMPUTER:
                gamesLost++;
                break;
            case TIE:
                ties++;
                break;
        }
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getTies() {
        return ties;
    }

    public void printSummary() {
        System.out.printf("Thanks for playing: %d games won, %d lost, and %d tied", gamesWon, gamesLost, ties);
    }
}
