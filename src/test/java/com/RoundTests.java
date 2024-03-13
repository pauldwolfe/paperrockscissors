package com;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class RoundTests {

    @Test
    void playRockPaperRound() {
        GameInputChoice player1ChoiceExpected = GameInputChoice.ROCK;
        GameInputAbstract player1Input = new GameInputComputer(new InputStreamSpecificCharacter('r'));
        Player player1 = new Player("player 1", player1Input);
    
        GameInputChoice player2ChoiceExpected = GameInputChoice.PAPER;
        GameInputAbstract player2Input = new GameInputComputer(new InputStreamSpecificCharacter('p'));
        Player player2 = new Player("player 2", player2Input);

        GameInputChoice winnerChoiceExpected = GameInputChoice.PAPER;
        GameInputChoice loserChoiceExpected = GameInputChoice.ROCK;
    
        RulesEngineRockPaperScissors rulesEngine = new RulesEngineRockPaperScissors();

        Round round = new Round(player1, player2, rulesEngine);
        try {
            if (round.PlayRound()) {
                assertEquals(round.getPlayer1Move().getGameInputChoice(), player1ChoiceExpected);
                assertEquals(round.getPlayer2Move().getGameInputChoice(), player2ChoiceExpected);
                assertEquals(round.getWinningPlayerMove().getGameInputChoice(), winnerChoiceExpected);
                assertEquals(round.getLosingPlayerMove().getGameInputChoice(), loserChoiceExpected);
            }
            else {
                fail("Round failed to play.");
            }
        } catch (Exception e) {
            fail("Round failed to play.");
        }

    }
}
