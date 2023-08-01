package com.hartwig.paperrockscissors

import com.hartwig.paperrockscissors.core.GameResult.*
import com.hartwig.paperrockscissors.game.Game
import com.hartwig.paperrockscissors.players.Player
import com.hartwig.paperrockscissors.strategy.impl.InputStreamStrategy
import com.hartwig.paperrockscissors.strategy.impl.RandomMoveStrategy
import java.text.SimpleDateFormat
import java.util.*

/**
 * Play a demo Rock Paper Scissors game against computer, using console for user input.
 */
fun main(args: Array<String>) {
    println("Let's play \"Paper Rock Scissors\" game!")
    println("Enter \"rock\", \"paper\", or \"scissors\" to play; \"exit\" to exit:")
    val game = Game()
    val human = Player("Human", InputStreamStrategy(System.`in`))
    val computer = Player("Computer", RandomMoveStrategy())
    var totalGames = 0;
    val startTime = System.currentTimeMillis()
    while (game.play(human, computer)) {
        totalGames++
    }
    val duration = System.currentTimeMillis() - startTime
    val format = SimpleDateFormat("mm:ss")
    println("\n=====================\nPlayed $totalGames games in ${format.format(Date(duration))}")
    println("Results:")
    printSumRecord(human, computer)
}

/**
 * Print the results for each player.
 */
fun printSumRecord(vararg players: Player) {
    for (player in players) {
        println("${player.name()}:")
        println("\tWins: ${player.getResults(WIN)}")
        println("\tDraws: ${player.getResults(DRAW)}")
        println("\tLoses: ${player.getResults(LOSE)}")
    }
}
