package com.hartwig.paperrockscissors.core

/**
 * Game moves available
 * TODO: Refactor. This class breaks SOLID principles ("Single Responsibility"): such as encapsulating game rules and "EXIT" command.
 */
enum class Move(private val beats: Int) {
    ROCK(2),
    PAPER(0),
    SCISSORS(1),
    EXIT(3) /* special value to exit game */;

    /**
     * Compare this move against the given move. Returns the [GameResult].
     */
    fun compare(move: Move): GameResult {
        if (this == move)
            return GameResult.DRAW
        else if (move == values()[this.beats]) {
            return GameResult.WIN
        } else {
            return GameResult.LOSE
        }
    }

}
