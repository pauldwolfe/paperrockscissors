package com.hartwig.paperrockscissors.game

import com.hartwig.paperrockscissors.core.GameResult
import com.hartwig.paperrockscissors.core.Move
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import com.hartwig.paperrockscissors.players.Player
import org.junit.jupiter.api.BeforeEach

internal class GameTest {

    private var playerA: Player = mock()
    private var playerB: Player = mock()
    private var game = Game()

    @BeforeEach
    fun reset() {
        reset(playerA)
        reset(playerB)
    }

    @Test
    fun testRockRock() {
        whenever(playerA.move()).thenReturn(Move.ROCK)
        whenever(playerB.move()).thenReturn(Move.ROCK)
        game.play(playerA, playerB)
        verify(playerA).move()
        verify(playerB).move()
        verify(playerA).recordGameResult(eq(GameResult.DRAW))
        verify(playerB).recordGameResult(eq(GameResult.DRAW))
    }

    @Test
    fun testRockScissors() {
        whenever(playerA.move()).thenReturn(Move.ROCK)
        whenever(playerB.move()).thenReturn(Move.SCISSORS)
        game.play(playerA, playerB)
        verify(playerA).move()
        verify(playerB).move()
        verify(playerA).recordGameResult(eq(GameResult.WIN))
        verify(playerB).recordGameResult(eq(GameResult.LOSE))
    }

    @Test
    fun testScissorsRock() {
        whenever(playerA.move()).thenReturn(Move.SCISSORS)
        whenever(playerB.move()).thenReturn(Move.ROCK)
        game.play(playerA, playerB)
        verify(playerA).move()
        verify(playerB).move()
        verify(playerA).recordGameResult(eq(GameResult.LOSE))
        verify(playerB).recordGameResult(eq(GameResult.WIN))
    }
}
