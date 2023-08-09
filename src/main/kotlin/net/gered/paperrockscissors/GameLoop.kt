package net.gered.paperrockscissors

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
    TODO()
}
