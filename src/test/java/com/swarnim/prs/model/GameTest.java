package com.swarnim.prs.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    @Test
    void testGameLogic_PlayerWins() {
        MoveStrategy playerStrategy = mock(MoveStrategy.class);
        when(playerStrategy.chooseMove()).thenReturn(Move.PAPER);

        MoveStrategy computerStrategy = mock(MoveStrategy.class);
        when(computerStrategy.chooseMove()).thenReturn(Move.ROCK);

        Game game = new Game(playerStrategy, computerStrategy);
        game.playRound();

        assertEquals(1, game.getPlayerWins());
        assertEquals(0, game.getComputerWins());
        assertEquals(0, game.getTies());
    }

    @Test
    void testGameLogic_ComputerWins() {
        MoveStrategy playerStrategy = mock(MoveStrategy.class);
        when(playerStrategy.chooseMove()).thenReturn(Move.SCISSORS);

        MoveStrategy computerStrategy = mock(MoveStrategy.class);
        when(computerStrategy.chooseMove()).thenReturn(Move.ROCK);

        Game game = new Game(playerStrategy, computerStrategy);
        game.playRound();

        assertEquals(0, game.getPlayerWins());
        assertEquals(1, game.getComputerWins());
        assertEquals(0, game.getTies());
    }

    @Test
    void testGameLogic_Tie() {
        MoveStrategy playerStrategy = mock(MoveStrategy.class);
        when(playerStrategy.chooseMove()).thenReturn(Move.ROCK);

        MoveStrategy computerStrategy = mock(MoveStrategy.class);
        when(computerStrategy.chooseMove()).thenReturn(Move.ROCK);

        Game game = new Game(playerStrategy, computerStrategy);
        game.playRound();

        assertEquals(0, game.getPlayerWins());
        assertEquals(0, game.getComputerWins());
        assertEquals(1, game.getTies());
    }
}
