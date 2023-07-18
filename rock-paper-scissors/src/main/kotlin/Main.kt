import rps.project.*

fun main() {
    println("Welcome to Rock Paper Scissors")
    println("Coding exercise - Evan Meikleham\n")

    // If you want to extend the game with more moves, it can be done here by defining Actions
    // and the Actions that they beat. As currently implemented, two distinct actions cannot create
    // a tie (i.e. an action can only be a tie with itself). It's not difficult to implement non-trivial
    // ties, but it greatly increases the number of edge cases, and doesn't really add anything to this exercise.
    // Please see the comments in Action.kt for further explanation.
    val actions = setOf(
        Action(MoveName.ROCK, setOf(MoveName.SCISSORS)),
        Action(MoveName.SCISSORS, setOf(MoveName.PAPER)),
        Action(MoveName.PAPER, setOf(MoveName.ROCK))
    )

    val playerFactory = PlayerFactory()
    val p1 = playerFactory.createPlayer(true)
    val p2 = playerFactory.createPlayer(false)
    val game = Game(p1, p2, actions)

    game.play()
}