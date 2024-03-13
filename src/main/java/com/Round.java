package com;

/**
 * Class that represents a round of the game, during the round an input is requested from each player, this input is given to the 
 * Rules Engine to get the winner. If it is a tie the winner is null (no winner).
 * At the end of each round a result of the round is printed to the console.
 */
public class Round {
    private Player player1 = null;
    private Player player2 = null;
    private PlayerMove player1Move = null;    
    private PlayerMove player2Move = null;
    private PlayerMove winningPlayerMove = null; // Remains null if it is a tie
    private PlayerMove losingPlayerMove = null; // Remains null if it is a tie
    private RulesEngineInterface rulesEngine = null;

    Round(Player player1, Player player2, RulesEngineInterface rulesEngine) {
        this.player1 = player1;
        this.player2 = player2;
        this.rulesEngine = rulesEngine;
    }

    /**
     * Plays a round, if either of the users ends the game then false is returned, else true is returned.
     * 
     * @return returns true if the round was played successfully, else returns false and this indicates the game should end.
     * @throws Exception
     */
    public boolean PlayRound() throws Exception {

        if (rulesEngine != null && player1 != null && player2 != null) {
            // Get the input from each player for this round.
            var player1Input = player1.getNextInput();
            var player2Input = player2.getNextInput();

            // If the input was NONE from either player the game is ended.
            if (player1Input == GameInputChoice.NONE || player2Input == GameInputChoice.NONE) {
                return false;
            }

            player1Move = new PlayerMove(player1, player1Input);
            player2Move = new PlayerMove(player2, player2Input);

            // If it is a tie the winningInput is NONE and the winningPlayerMove is null.
            var winningInput = rulesEngine.applyRule(player1Input, player2Input);

            if (winningInput != GameInputChoice.NONE) {
                if (winningInput == player1Input) {
                    winningPlayerMove = player1Move;
                    losingPlayerMove = player2Move;
                } else {
                    winningPlayerMove = player2Move;
                    losingPlayerMove = player1Move;
                }
            }
            System.out.println(getRoundString());
        }
        else {
            throw new Exception("An error occurred while trying to play a round.");
        }

        return true;
    }

    public PlayerMove getPlayer1Move() {
        return player1Move;
    }

    public PlayerMove getPlayer2Move() {
        return player2Move;
    }

    public PlayerMove getWinningPlayerMove() {
        return winningPlayerMove;
    }

    public PlayerMove getLosingPlayerMove() {
        return losingPlayerMove;
    }

    /**
     * Gets a String of the round's results formatted in a presentable manner.
     * 
     * @return the result of the round as a fromatted String
     */
    public String toString() {
        return getRoundString();
    }

    private String getRoundString() {
        String msg = TextColors.CYAN +
                    player1Move.getPlayer().getName() + " played " + player1Move.getGameInputChoice() + " & " + 
                    player2Move.getPlayer().getName() + " played " + player2Move.getGameInputChoice() + " -->\t" + TextColors.WHITE;
        if (winningPlayerMove == null) {
            msg += TextColors.YELLOW + "It is a TIE" + TextColors.WHITE;
        } else {
            msg += TextColors.YELLOW + winningPlayerMove.getGameInputChoice() + " wins " + losingPlayerMove.getGameInputChoice() + ". " +
                   TextColors.GREEN + "\t** The winner is " + winningPlayerMove.getPlayer().getName() + "! **" + TextColors.WHITE;
        }
        return msg;
    }

}
