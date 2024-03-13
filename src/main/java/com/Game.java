package com;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Game class creates all the components required to play the game, that is:
 * - Two players, a person player and a computer player.
 * - Game input source instances, one for commandline for the person player and one for random input for the computer player.
 * - List of game rounds, a new round is created and played in a loop until the user ends the game.
 */
public class Game {

    private RulesEngineRockPaperScissors rulesEngine = new RulesEngineRockPaperScissors();
    private List<Round> rounds = new ArrayList<Round>();
    private Player playerComputer = null;
    private Player playerPerson = null;
    private int playerPersonCountWins = 0;
    private int playerComputerCountWins = 0;

    public List<Round> getRounds() {
        return rounds;
    }

    /**
     * Play the game. Create the components required for the game and play rounds in a loop until the user ends the game.
     * 
     * @throws Exception
     */
    public void Play() throws Exception {

        boolean endGame = false;

        // Create the game input instances.
        GameInputAbstract commandlineInput = new GameInputCommandline(System.in);
        GameInputAbstract computerInput = new GameInputComputer(new InputStreamRandomGameChoiceCharacter());

        // Ask the players their names.
        String personName = commandlineInput.requestString("Please enter your name");
        String computerName = computerInput.requestString("");
        // If null is returned for a name the game ends.
        if (personName == null || computerName == null) {
            return;
        }

        // Create the players instances.
        playerComputer = new Player(computerName, computerInput);
        playerPerson = new Player(personName, commandlineInput);

        // Loop playing rounds until the user ends the game.
        while (!endGame) {
            // When a round returns false then the game is ended.
            Round newRound = new Round(playerComputer, playerPerson, rulesEngine);
            System.out.println("\n---------------------- ROUND " + (rounds.size()+1) + " ----------------------");
            if (newRound.PlayRound()) {
                rounds.add(newRound);
                if (newRound.getWinningPlayerMove() != null) {
                    if (newRound.getWinningPlayerMove().getPlayer() == playerPerson) {
                        playerPersonCountWins++;
                    }
                    if (newRound.getWinningPlayerMove().getPlayer() == playerComputer) {
                        playerComputerCountWins++;
                    }
                }
            } else {
                endGame = true;
            }
        }
    }

    /**
     * Converts the result of the game into a printable summary as a String.
     * 
     * @return  the formatted String of the summary of the game.
     */
    public String toString() {
        int roundsCounter = 1;
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        for (Round round : rounds) {
            printWriter.println("\nROUND " + roundsCounter + ":");
            printWriter.print(round.toString());
            roundsCounter++;
        }

        printWriter.println("\n\nFINAL SCORE");
        printWriter.println("-----------");
        printWriter.println("Total Rounds: " + roundsCounter + ":");
        printWriter.println(playerPerson.getName() + ": " + playerPersonCountWins);
        printWriter.println(playerComputer.getName() + ": " + playerComputerCountWins);
        String finalWinnerString;
        if (playerPersonCountWins > playerComputerCountWins) {
            finalWinnerString = playerPerson.getName().toUpperCase();
        } else if (playerComputerCountWins > playerPersonCountWins) {
            finalWinnerString = playerComputer.getName().toUpperCase();
        } else {
            finalWinnerString = "NOBODY, it is a TIE";
        }

        String finalWinnerLabel = "THE FINAL WINNER IS: ";

        for (int i = 0; i < (finalWinnerLabel.length() + finalWinnerString.length() + 6); i++){
            printWriter.print(TextColors.GREEN + "-");
        }
        printWriter.print("\n" + TextColors.GREEN + "|  " + finalWinnerLabel + TextColors.RED + finalWinnerString + TextColors.GREEN + "  |\n");
        for (int i = 0; i < (finalWinnerLabel.length() + finalWinnerString.length() + 6); i++){
            printWriter.print(TextColors.GREEN + "-");
        }
        // Reset text color to white
        printWriter.print(TextColors.WHITE);

        return stringWriter.toString();
    }
}
