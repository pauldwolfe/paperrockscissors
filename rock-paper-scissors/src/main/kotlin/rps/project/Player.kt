package rps.project

import java.util.*

interface Player {
    fun getMove(allowQuit: Boolean): MoveName
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
}

class ComputerPlayer : Player {
    override fun getMove(allowQuit: Boolean): MoveName {
        val list = listOf(MoveName.ROCK, MoveName.PAPER, MoveName.SCISSORS)
        return list.random()
    }
}

class PlayerFactory {
    fun createPlayer(isHuman: Boolean): Player {
        return if (isHuman) HumanPlayer() else ComputerPlayer()
    }
}