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

    /**
     * Tries to convert the player input to a [Choice], or returns null if the player input does not represent one of
     * the possible match choices.
     */
    fun toChoice(): Choice? {
        return when (this) {
            ROCK -> Choice.ROCK
            PAPER -> Choice.PAPER
            SCISSORS -> Choice.SCISSORS
            else -> null
        }
    }

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
        val matchNum = matches.total + 1
        println("\nMatch #$matchNum")

        println("Make your choice! (type 'rock', 'paper' or 'scissors'. type 'quit' to stop playing)")
        val input = PlayerInput.fromString(readln())
        logger.debug("Parsed player input: {}", input)

        when (input) {
            PlayerInput.QUIT -> break
            else -> {
                val playerChoice = input.toChoice()
                if (playerChoice == null) {
                    println("I didn't understand that, please try again.")
                } else {
                    println("Your choice: $playerChoice")
                    val match = playMatch(playerChoice)
                    println("I chose ${match.computer}!")

                    when (match.result) {
                        Result.LEFT -> println("${match.player} beats ${match.computer}! You win!")
                        Result.RIGHT -> println("${match.computer} beats ${match.player}! I win!")
                        Result.TIE -> println("It's a tie!")
                    }
                    matches.record(match)
                }
            }
        }
    }
}
