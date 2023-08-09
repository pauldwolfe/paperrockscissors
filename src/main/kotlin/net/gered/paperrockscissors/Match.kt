package net.gered.paperrockscissors

import org.slf4j.LoggerFactory

// TODO: perhaps using "yes/no/tie" values instead would be more descriptive / intuitive ... ?
/**
 * The winning side of a played match.
 */
enum class Result {
    LEFT,
    RIGHT,
    TIE,
}

/**
 * A player's choice in a match.
 */
enum class Choice {
    ROCK {
        override fun beats(right: Choice): Result {
            return when (right) {
                ROCK -> Result.TIE
                PAPER -> Result.RIGHT
                SCISSORS -> Result.LEFT
            }
        }
    },
    PAPER {
        override fun beats(right: Choice): Result {
            return when (right) {
                ROCK -> Result.LEFT
                PAPER -> Result.TIE
                SCISSORS -> Result.RIGHT
            }
        }
    },
    SCISSORS {
        override fun beats(right: Choice): Result {
            return when (right) {
                ROCK -> Result.RIGHT
                PAPER -> Result.LEFT
                SCISSORS -> Result.TIE
            }
        }
    };

    /**
     * Determines which side wins against this choice (considered the "left" side) and the other choice given
     * (considered the "right" side, as per the argument name).
     *
     * @param right the other choice in the match
     *
     * @return the winning side of the two choices
     */
    abstract fun beats(right: Choice): Result
}

class Matches {
    companion object {
        private val logger = LoggerFactory.getLogger(Matches::class.java)
    }

    var wins = 0
        private set
    var losses = 0
        private set
    var ties = 0
        private set

    val total get() = wins + losses + ties

    fun playMatch(playerChoice: Choice): Result {
        val computerChoice = Choice.values().random()
        val result = playerChoice.beats(computerChoice)
        logger.debug(
            "Match played with player {} against computer {} with result {}",
            playerChoice,
            computerChoice,
            result
        )
        when (result) {
            Result.LEFT -> {
                logger.debug("Recording player win for match result {}", result)
                wins += 1
            }

            Result.RIGHT -> {
                logger.debug("Recording player loss for match result {}", result)
                losses += 1
            }

            Result.TIE -> {
                logger.debug("Recording tie for match result {}", result)
                ties += 1
            }
        }
        return result
    }
}