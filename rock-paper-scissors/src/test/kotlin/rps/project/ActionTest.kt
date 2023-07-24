package rps.project

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ActionTest {

    @Test
    fun testActionTiesItself() {
        val action1 = Action(MoveName.ROCK, setOf())
        assertEquals(action1.getOutcome(action1), Outcome.TIE)
    }

    @Test
    fun testAction1BeatsAction2AndAction2DoesNotBeatAction1() {
        val action1 = Action(MoveName.ROCK, setOf(MoveName.SCISSORS))
        val action2 = Action(MoveName.SCISSORS, setOf())
        assertEquals(action1.getOutcome(action2), Outcome.WIN)
    }

    @Test
    fun testAction1BeatsAction2AndAction2BeatsAction1() {
        val action1 = Action(MoveName.ROCK, setOf(MoveName.SCISSORS))
        val action2 = Action(MoveName.SCISSORS, setOf(MoveName.ROCK))
        assertEquals(action1.getOutcome(action2), Outcome.UNDEFINED)
    }

    @Test
    fun testAction1DoesNotBeatAction2AndAction2BeatsAction1() {
        val action1 = Action(MoveName.ROCK, setOf())
        val action2 = Action(MoveName.SCISSORS, setOf(MoveName.ROCK))
        assertEquals(action1.getOutcome(action2), Outcome.LOSS)
    }

    @Test
    fun testAction1DoesNotBeatAction2AndAction2DoesNotBeatAction1() {
        val action1 = Action(MoveName.ROCK, setOf(MoveName.PAPER))
        val action2 = Action(MoveName.SCISSORS, setOf(MoveName.PAPER))
        assertEquals(action1.getOutcome(action2), Outcome.UNDEFINED)
    }

}
