package com.hartwig.paperrockscissors.players

import com.hartwig.paperrockscissors.core.GameResult
import com.hartwig.paperrockscissors.core.Move
import com.hartwig.paperrockscissors.strategy.Strategy

/**
 * Player of the game.
 */
class Player(private val name: String, private val strategy: Strategy) {

    /**
     * Results of all games for this player.
     */
    private val results = HashMap<GameResult, Int>()

    /**
     * Name of this player.
     */
    fun name(): String {
        return name
    }

    /**
     * The "move" of the player.
     */
    fun move(): Move {
        val move = strategy.move()
        println("${name()}>>> $move")
        return move
    }

    /**
     * Records game result.
     */
    fun recordGameResult(result: GameResult) {
        results[result] = results.getOrDefault(result, 0) + 1
    }

    /**
     * Retrieves total number of games ended with specified result.
     */
    open fun getResults(result: GameResult): Int {
        return results.getOrDefault(result, 0)
    }
}

