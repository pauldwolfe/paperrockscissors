package com.swarnim.prs.model;

import lombok.Getter;

@Getter
public class Game {
    private final MoveStrategy player;
    private final MoveStrategy computer;
    private int gamesPlayed;
    private int playerWins;
    private int computerWins;
    private int ties;

    public Game(MoveStrategy player, MoveStrategy computer) {
        this.player = player;
        this.computer = computer;
        gamesPlayed = 0;
        playerWins = 0;
        computerWins = 0;
        ties = 0;
    }

    public int playRound() {

        Move playerMove = player.chooseMove();
        if (playerMove == Move.QUIT) {
            return 0;
        }

        gamesPlayed++;
        Move computerMove = computer.chooseMove();

        System.out.println(player.getName() + " chose " + playerMove);
        System.out.println("Computer chose " + computerMove);

        if (playerMove == computerMove) {
            ties++;
            System.out.println("It's a tie!");
        } else if ((playerMove == Move.ROCK && computerMove == Move.SCISSORS) ||
                (playerMove == Move.PAPER && computerMove == Move.ROCK) ||
                (playerMove == Move.SCISSORS && computerMove == Move.PAPER)) {
            playerWins++;
            System.out.println(player.getName() + " wins!");
        } else {
            computerWins++;
            System.out.println("Computer wins!");
        }
        return 1;
    }

    public void displaySummary() {
        System.out.println("\nGames played: " + gamesPlayed);
        System.out.println("Player wins: " + playerWins);
        System.out.println("Computer wins: " + computerWins);
        System.out.println("Ties: " + ties);
    }
}
