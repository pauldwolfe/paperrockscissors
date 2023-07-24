package rps.project

import java.util.*

interface Player {
    fun getMove(allowQuit: Boolean): MoveName
    fun displayCallback(a1: Action, a2: Action, oc: Outcome): Unit
}

class HumanPlayer : Player {
    private val reader = Scanner(System.`in`)

    override fun getMove(allowQuit: Boolean): MoveName {
        val msg = if (allowQuit) "R = rock, P = paper, S = scissors, Q = quit" else "R = rock, P = paper, S = scissors"

        while(true) {
            println("Please choose your move.")
            println(msg)
            val input = reader.nextLine()
            if (input.equals("R", true)) { return MoveName.ROCK }
            if (input.equals("P", true)) { return MoveName.PAPER }
            if (input.equals("S", true)) { return MoveName.SCISSORS }
            if (allowQuit && input.equals("Q", true)) { return MoveName.END_GAME }
            println("Unknown input")
        }
    }

    // TODO: Make the display different for player 1 vs player 2, in case of two human players
    override fun displayCallback(a1: Action, a2: Action, oc: Outcome) {
        println("You chose ${a2.name}")
    }
}

class ComputerPlayer : Player {
    override fun getMove(allowQuit: Boolean): MoveName {
        val list = listOf(MoveName.ROCK, MoveName.PAPER, MoveName.SCISSORS)
        return list.random()
    }

    override fun displayCallback(a1: Action, a2: Action, oc: Outcome) {
        println("Your opponent chose ${a2.name}.\nP1: ${a1.name}, P2: ${a2.name}, Outcome: ${oc.name}\n")
    }
}

class TestPlayer : Player {

    private var moveList: List<MoveName> = listOf()
    private var iterator: ListIterator<MoveName> = moveList.listIterator()
    fun setMoves(movesToPlay: List<MoveName>) {
        moveList = movesToPlay
        iterator = moveList.listIterator()
    }

    override fun getMove(allowQuit: Boolean): MoveName {
        if (!iterator.hasNext()) {
            return MoveName.END_GAME
        }

        return iterator.next()
    }

    override fun displayCallback(a1: Action, a2: Action, oc: Outcome) {
        // No-op in the case of testing. We don't want anything displayed.
    }

}

class PlayerFactory {
    fun createPlayer(isHuman: Boolean): Player {
        return if (isHuman) HumanPlayer() else ComputerPlayer()
    }

    // Since TestPlayer is only used for testing, I'm not including it in the main createPlayer function
    fun createTestPlayer(moveList: List<MoveName>): TestPlayer {
        val testPlayer = TestPlayer()
        testPlayer.setMoves(moveList)
        return testPlayer
    }
}