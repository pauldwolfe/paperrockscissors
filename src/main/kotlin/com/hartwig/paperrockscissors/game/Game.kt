package com.hartwig.paperrockscissors.game

import com.hartwig.paperrockscissors.core.GameResult
import  com.hartwig.paperrockscissors.core.Move.EXIT
import com.hartwig.paperrockscissors.players.Player

/**
 * Implementation of a single game for two players.
 */
class Game {
    /**
     * Plays the game and records the result.
     *
     * @return false if user entered 'exit', or true if user continues to play
     */
    fun play(a: Player, b: Player): Boolean {
        val aMove = a.move()
        val bMove = b.move()
        if (aMove == EXIT || bMove == EXIT) return false
        when (aMove.compare(bMove)) {
            GameResult.WIN -> {
                println("Winner: ${a.name()}")
                a.recordGameResult(GameResult.WIN)
                b.recordGameResult(GameResult.LOSE)
            }

            GameResult.LOSE -> {
                println("Winner: ${b.name()}")
                a.recordGameResult(GameResult.LOSE)
                b.recordGameResult(GameResult.WIN)
            }

            GameResult.DRAW -> {
                println("Draw!")
                a.recordGameResult(GameResult.DRAW)
                b.recordGameResult(GameResult.DRAW)
            }
        }
        return true
    }
}
