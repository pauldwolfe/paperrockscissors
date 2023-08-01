package com.hartwig.paperrockscissors.core

/**
 * Game moves available
 */
enum class Move(private val beats: Int) {
	ROCK(2),
	PAPER(0),
	SCISSORS(1),
	EXIT(3) /* special value to exit game */;

	/**
	 * Compare this move against the given move. Returns the [GameResult] of comparing the two moves
	 * from the perspective of this move.
	 */
	fun compare(move: Move): GameResult {
		if (this == move)
			return GameResult.DRAW
		return if (move == values()[this.beats]) {
			GameResult.WIN
		} else {
			GameResult.LOSE
		}
	}

}
