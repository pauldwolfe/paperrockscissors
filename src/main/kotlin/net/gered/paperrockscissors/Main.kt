package net.gered.paperrockscissors

fun main(args: Array<String>) {
    var matches = Matches()

    println("Let's Play Paper-Rock-Scissors!!!")

    runGameLoop(matches)

    println("In total we played ${matches.total} matches:")
    println("    - You won ${matches.wins} times")
    println("    - You lost ${matches.losses} times")
    println("    - We tied ${matches.ties} times")
    println()
    println("Thanks for playing!")
}