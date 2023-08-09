package net.gered.paperrockscissors

import kotlin.test.Test
import kotlin.test.assertEquals

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
}