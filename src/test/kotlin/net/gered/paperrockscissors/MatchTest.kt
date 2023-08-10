package net.gered.paperrockscissors

import kotlin.test.Test
import kotlin.test.assertEquals

internal class MatchTest {
    @Test
    fun testRockAgainst() {
        assertEquals(Result.TIE, Choice.ROCK.beats(Choice.ROCK))
        assertEquals(Result.RIGHT, Choice.ROCK.beats(Choice.PAPER))
        assertEquals(Result.LEFT, Choice.ROCK.beats(Choice.SCISSORS))
    }

    @Test
    fun testPaperAgainst() {
        assertEquals(Result.LEFT, Choice.PAPER.beats(Choice.ROCK))
        assertEquals(Result.TIE, Choice.PAPER.beats(Choice.PAPER))
        assertEquals(Result.RIGHT, Choice.PAPER.beats(Choice.SCISSORS))
    }

    @Test
    fun testScissorsAgainst() {
        assertEquals(Result.RIGHT, Choice.SCISSORS.beats(Choice.ROCK))
        assertEquals(Result.LEFT, Choice.SCISSORS.beats(Choice.PAPER))
        assertEquals(Result.TIE, Choice.SCISSORS.beats(Choice.SCISSORS))
    }

    @Test
    fun testRecordingMatchesAccumulatesResultsAccurately() {
        var matches = Matches()
        assertEquals(0, matches.total)
        assertEquals(0, matches.wins)
        assertEquals(0, matches.losses)
        assertEquals(0, matches.ties)

        matches.record(Match(Choice.ROCK, Choice.SCISSORS, Result.LEFT))
        assertEquals(1, matches.total)
        assertEquals(1, matches.wins)
        assertEquals(0, matches.losses)
        assertEquals(0, matches.ties)

        matches.record(Match(Choice.PAPER, Choice.ROCK, Result.LEFT))
        assertEquals(2, matches.total)
        assertEquals(2, matches.wins)
        assertEquals(0, matches.losses)
        assertEquals(0, matches.ties)

        matches.record(Match(Choice.SCISSORS, Choice.ROCK, Result.RIGHT))
        assertEquals(3, matches.total)
        assertEquals(2, matches.wins)
        assertEquals(1, matches.losses)
        assertEquals(0, matches.ties)

        matches.record(Match(Choice.PAPER, Choice.PAPER, Result.TIE))
        assertEquals(4, matches.total)
        assertEquals(2, matches.wins)
        assertEquals(1, matches.losses)
        assertEquals(1, matches.ties)
    }
}