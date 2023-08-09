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
}