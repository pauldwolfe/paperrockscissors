package net.gered.paperrockscissors

fun main(args: Array<String>) {
    var matches = Matches()

    matches.playMatch(Choice.ROCK);
    matches.playMatch(Choice.PAPER);
    matches.playMatch(Choice.SCISSORS);

    println("Played ${matches.total}:")
    println("    - You won ${matches.wins} times")
    println("    - I won ${matches.losses} times")
    println("    - We tied ${matches.ties} times")
}