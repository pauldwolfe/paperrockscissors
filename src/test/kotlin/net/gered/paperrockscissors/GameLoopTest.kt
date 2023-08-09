package net.gered.paperrockscissors

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

internal class GameLoopTest {
    @Test
    fun testPlayerInputParsing() {
        assertEquals(PlayerInput.ROCK, PlayerInput.fromString("rock"))
        assertEquals(PlayerInput.ROCK, PlayerInput.fromString("ROCK"))
        assertEquals(PlayerInput.ROCK, PlayerInput.fromString(" rock"))
        assertEquals(PlayerInput.ROCK, PlayerInput.fromString("rock "))

        assertEquals(PlayerInput.PAPER, PlayerInput.fromString("paper"))
        assertEquals(PlayerInput.SCISSORS, PlayerInput.fromString("scissors"))
        assertEquals(PlayerInput.QUIT, PlayerInput.fromString("quit"))

        assertEquals(PlayerInput.INVALID, PlayerInput.fromString(""))
        assertEquals(PlayerInput.INVALID, PlayerInput.fromString("foobar"))
    }

    @Test
    fun testPlayerInputConversionToChoice() {
        assertEquals(Choice.ROCK, PlayerInput.ROCK.toChoice())
        assertEquals(Choice.PAPER, PlayerInput.PAPER.toChoice())
        assertEquals(Choice.SCISSORS, PlayerInput.SCISSORS.toChoice())
        assertNull(PlayerInput.QUIT.toChoice())
        assertNull(PlayerInput.INVALID.toChoice())
    }
}