package com.github.bartjohn;

public class PaperRockScissors {
    public enum Outcome {
        TIE,
        USER,
        COMPUTER
    }

    private final Outcome[] OUTCOMES = Outcome.values();
    private final String[] SELECTIONS = new String[]{"PAPER", "ROCK", "SCISSORS"};

    private final ComputerPlayer computerPlayer;
    private final GameHistory gameHistory = new GameHistory();

    public PaperRockScissors(ComputerPlayer computerPlayer) {
        this.computerPlayer = computerPlayer;
    }

    public void play(int userSelection) throws Exception {
        validateSelection(userSelection);
        System.out.printf("The user chose %s\n", SELECTIONS[userSelection]);

        int computerSelection = computerPlayer.makeSelection();
        validateSelection(computerSelection);
        System.out.printf("The computer chose %s\n", SELECTIONS[computerSelection]);

        Outcome outcome = applyRules(userSelection, computerSelection);
        gameHistory.recordOutcome(outcome);
        System.out.printf("Winner: %s\n", outcome);
    }

    public GameHistory getGameHistory() {
        return gameHistory;
    }

    public void exit() {
        gameHistory.printSummary();
    }

    private Outcome applyRules(int userSelection, int computerSelection) {
        /*
            Use the difference between selections to determine the game's outcome:
              0 - the outcome is a tie.
              1 - the computer's selection was 1 higher than the user's (e.g. ROCK vs. PAPER) and the user wins.
              2 - the computer's selection was 2 higher than the user's (e.g. SCISSORS vs. PAPER) and the computer wins.
              <0 - due to the cyclical nature of the rules, we can add 3 to get a usable result
         */
        int difference = computerSelection - userSelection;
        if (difference < 0) {
            difference += 3;
        }
        return OUTCOMES[difference];
    }

    private void validateSelection(int selection) throws Exception {
        if (selection < 0 || selection > SELECTIONS.length - 1) {
            throw new ValidationException(String.format("Invalid selection: selections must be between 0 and %d",
                    SELECTIONS.length - 1));
        }
    }
}
