package rps.project

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import rps.project.Action
import rps.project.MoveName
import rps.project.Outcome

class ActionTest {
    val rock = Action(MoveName.ROCK, setOf(MoveName.SCISSORS))
    val scissors = Action(MoveName.SCISSORS, setOf(MoveName.PAPER))
    val paper = Action(MoveName.PAPER, setOf(MoveName.ROCK))

    @Test
    fun testRock() {
        val againstRock = rock.getOutcome(rock)
        val againstScissors = rock.getOutcome(scissors)
        val againstPaper = rock.getOutcome(paper)
        assertEquals(againstRock, Outcome.TIE, "Rock ties Rock")
        assertEquals(againstScissors, Outcome.WIN, "Rock beats Scissors")
        assertEquals(againstPaper, Outcome.LOSS, "Rock loses to Paper")
    }

    @Test
    fun testScissors() {
        val againstRock = scissors.getOutcome(rock)
        val againstScissors = scissors.getOutcome(scissors)
        val againstPaper = scissors.getOutcome(paper)
        assertEquals(againstRock, Outcome.LOSS, "Scissors loses to Rock")
        assertEquals(againstScissors, Outcome.TIE, "Scissors ties Scissors")
        assertEquals(againstPaper, Outcome.WIN, "Scissors beats Paper")
    }

    @Test
    fun testPaper() {
        val againstRock = paper.getOutcome(rock)
        val againstScissors = paper.getOutcome(scissors)
        val againstPaper = paper.getOutcome(paper)
        assertEquals(againstRock, Outcome.WIN, "Paper beats Rock")
        assertEquals(againstScissors, Outcome.LOSS, "Paper loses to Scissors")
        assertEquals(againstPaper, Outcome.TIE, "Paper ties Paper")
    }

    // If we add new moves, make sure the logic is consistent
    val modifiedRock = Action(MoveName.ROCK, setOf(MoveName.SCISSORS, MoveName.PAPER))
    val modifiedScissors = Action(MoveName.SCISSORS, setOf(MoveName.SCISSORS, MoveName.ROCK))
    val modifiedPaper = Action(MoveName.PAPER, setOf())
    @Test
    fun testModifiedActions() {
        val test1 = modifiedRock.getOutcome(modifiedRock)
        val test2 = modifiedRock.getOutcome(modifiedScissors)
        val test3 = modifiedRock.getOutcome(modifiedPaper)
        val test4 = modifiedScissors.getOutcome(modifiedRock)
        val test5 = modifiedScissors.getOutcome(modifiedScissors)
        val test6 = modifiedScissors.getOutcome(modifiedPaper)
        val test7 = modifiedPaper.getOutcome(modifiedRock)
        val test8 = modifiedPaper.getOutcome(modifiedScissors)
        val test9 = modifiedPaper.getOutcome(modifiedPaper)
        assertEquals(test1, Outcome.TIE, "An action always ties itself, regardless of the dictionary")
        assertEquals(test2, Outcome.UNDEFINED, "Rock > Scissors and Scissors > Rock, so no defined winner")
        assertEquals(test3, Outcome.WIN, "Rock > paper in the modified game")
        assertEquals(test4, Outcome.UNDEFINED, "Rock > Scissors and Scissors > Rock, so no defined winner")
        assertEquals(test5, Outcome.TIE, "An action always ties itself, regardless of the dictionary")
        assertEquals(test6, Outcome.UNDEFINED, "Paper !> Scissors and Scissors !> Paper, so no defined winner")
        assertEquals(test7, Outcome.LOSS, "Rock > paper in the modified game")
        assertEquals(test8, Outcome.UNDEFINED, "Paper !> Scissors and Scissors !> Paper, so no defined winner")
        assertEquals(test9, Outcome.TIE, "An action always ties itself, regardless of the dictionary")
    }

}
