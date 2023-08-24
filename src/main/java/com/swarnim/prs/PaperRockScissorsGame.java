package com.swarnim.prs;

import com.swarnim.prs.model.Computer;
import com.swarnim.prs.model.Game;
import com.swarnim.prs.model.MoveStrategy;
import com.swarnim.prs.model.Player;

import java.util.Scanner;

public class PaperRockScissorsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        MoveStrategy player = new Player(playerName);
        MoveStrategy computer = new Computer();

        Game game = new Game(player, computer);
        int round;

        do {
            round = game.playRound();
        } while (round == 1);

        game.displaySummary();
    }
}
