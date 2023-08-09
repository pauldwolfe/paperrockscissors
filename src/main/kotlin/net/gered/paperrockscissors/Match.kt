package net.gered.paperrockscissors

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

