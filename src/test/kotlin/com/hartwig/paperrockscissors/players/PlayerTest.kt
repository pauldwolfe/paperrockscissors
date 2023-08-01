package com.hartwig.paperrockscissors.players

import com.hartwig.paperrockscissors.core.GameResult
import com.hartwig.paperrockscissors.core.Move
import com.hartwig.paperrockscissors.players.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import com.hartwig.paperrockscissors.strategy.Strategy
import com.hartwig.paperrockscissors.strategy.impl.RandomMoveStrategy

internal class PlayerTest {

    @Test
    fun testName() {
        val player = Player("Computer", RandomMoveStrategy())
        assert(player.name() == "Computer")
    }

    @Test
    fun testMove() {
        var strategy: Strategy = mock()
        whenever(strategy.move()).thenReturn(Move.ROCK)
        val player = Player("", strategy)
        assertEquals(Move.ROCK, player.move())
    }

    @Test
    fun testRecord() {
        var strategy: Strategy = mock()
        val player = Player("", strategy)
        assertEquals(player.getResults(GameResult.DRAW), 0)
        player.recordGameResult(GameResult.DRAW)
        assertEquals(player.getResults(GameResult.DRAW), 1)
    }

}

