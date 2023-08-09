package net.gered.paperrockscissors

// TODO: perhaps using "yes/no/tie" values instead would be more descriptive / intuitive ... ?
enum class Result {
    LEFT,
    RIGHT,
    TIE,
}

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

    abstract fun beats(right: Choice) : Result
}