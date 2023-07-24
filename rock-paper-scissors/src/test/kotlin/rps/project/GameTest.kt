package rps.project

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameTest {
    private val playerFactory = PlayerFactory()
    private val player1 = playerFactory.createTestPlayer(listOf())
    private val player2 = playerFactory.createTestPlayer(listOf())
    private val actions = setOf(
        Action(MoveName.ROCK, setOf(MoveName.SCISSORS)),
        Action(MoveName.SCISSORS, setOf(MoveName.PAPER)),
        Action(MoveName.PAPER, setOf(MoveName.ROCK))
    )
    private val game = Game(player1, player2, actions)

    // The game specs say that the standard Rock-Paper-Scissors rules need to hold, so this exhaustively tests
    // the standard set of outcomes
    @ParameterizedTest
    @MethodSource("standardMoveArgumentProvider")
    fun paramTestAllCases(p1move: MoveName, p2move: MoveName, expected: Outcome, testString: String) {
        game.reset()
        player1.setMoves(listOf(p1move))
        player2.setMoves(listOf(p2move))
        game.play()
        val expectedRecord = Record.outcomeToRecord(expected)
        val actualRecord = game.getFinalResult{}
        Assertions.assertTrue(actualRecord.equals(expectedRecord), testString)
    }

    @Test
    fun testSequentialMoves() {

        // Just a random sequence of moves, but it contains at least one win, one loss, and one tie
        val player1moves = listOf(
            MoveName.ROCK, MoveName.PAPER, MoveName.ROCK, MoveName.SCISSORS, MoveName.SCISSORS
        )

        val player2moves = listOf(
            MoveName.ROCK, MoveName.ROCK, MoveName.SCISSORS, MoveName.SCISSORS, MoveName.ROCK
        )

        player1.setMoves(player1moves)
        player2.setMoves(player2moves)
        game.reset()
        game.play()

        val expectedRecord = Record(2, 1,2)
        val actualRecord = game.getFinalResult{}

        Assertions.assertTrue(actualRecord.equals(expectedRecord), "Game properly processes sequential moves")

    }

    companion object  {
        @JvmStatic
        fun standardMoveArgumentProvider(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(MoveName.ROCK, MoveName.ROCK, Outcome.TIE, "Rock ties Rock"),
                Arguments.of(MoveName.ROCK, MoveName.PAPER, Outcome.LOSS, "Rock loses to Paper"),
                Arguments.of(MoveName.ROCK, MoveName.SCISSORS, Outcome.WIN, "Rock beats Scissors"),
                Arguments.of(MoveName.PAPER, MoveName.ROCK, Outcome.WIN, "Paper beats Rock"),
                Arguments.of(MoveName.PAPER, MoveName.PAPER, Outcome.TIE, "Paper ties Paper"),
                Arguments.of(MoveName.PAPER, MoveName.SCISSORS, Outcome.LOSS, "Paper loses to Scissors"),
                Arguments.of(MoveName.SCISSORS, MoveName.ROCK, Outcome.LOSS, "Scissors loses to Rock"),
                Arguments.of(MoveName.SCISSORS, MoveName.PAPER, Outcome.WIN, "Scissors beats Paper"),
                Arguments.of(MoveName.SCISSORS, MoveName.SCISSORS, Outcome.TIE, "Scissors ties Scissors"),
            )
        }
    }
}