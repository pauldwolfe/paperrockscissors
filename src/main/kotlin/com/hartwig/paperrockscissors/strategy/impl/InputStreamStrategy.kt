package com.hartwig.paperrockscissors.strategy.impl

import com.hartwig.paperrockscissors.core.Move
import com.hartwig.paperrockscissors.strategy.Strategy
import java.io.InputStream
import java.util.*

/**
 * This strategy retrieves user input (or any other input) from InputStream
 */
class InputStreamStrategy(inputStream: InputStream) : Strategy {
    val inputScanner = Scanner(inputStream)
    override fun move(): Move {
        var move: Move? = null
        while (move == null) {
            var input = inputScanner.nextLine().uppercase(Locale.getDefault())
            try {
                move = Move.valueOf(input)
            } catch (e: IllegalArgumentException) {
                println("You entered invalid value: $input; please enter again.")
                println("Enter \"rock\", \"paper\", or \"scissors\" to play; \"exit\" to exit.")
            }
        }
        return move
    }
}