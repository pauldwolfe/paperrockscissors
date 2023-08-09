package net.gered.paperrockscissors

import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("GameLoop")

/**
 * Parsed player input from what was typed in during the main game loop prompt.
 */
enum class PlayerInput {
    ROCK,
    PAPER,
    SCISSORS,
    QUIT,
    INVALID;

    companion object {
        fun fromString(input: String) : PlayerInput {
            return when (input.lowercase().trim()) {
                "rock" -> ROCK
                "paper" -> PAPER
                "scissors" -> SCISSORS
                "quit" -> QUIT
                else -> INVALID
            }
        }
    }
}

fun runGameLoop(matches: Matches) {
    while (true) {
        println("Make your choice! (type 'rock', 'paper' or 'scissors'. type 'quit' to stop playing)")
        val input = PlayerInput.fromString(readln())
        logger.debug("Parsed player input: {}", input)
        when (input) {
            PlayerInput.QUIT -> break
            PlayerInput.INVALID -> println("I didn't understand that, please try again.")
            else -> {
                // todo
            }
        }
    }
}
