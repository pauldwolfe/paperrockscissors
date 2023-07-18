package rps.project

enum class PlayerName {
    PLAYER1, PLAYER2
}

enum class MoveName {
    ROCK, PAPER, SCISSORS, END_GAME
}

class Game(private val p1: Player, private val p2: Player, actions: Set<Action>) {

    private val actionMap = actions.associateBy({ a -> a.name }, { a -> a } )

    // Not strictly necessary, but it cleans up some of the code to use an empty action as a starting state
    private val emptyAction = Action(null, setOf())

    private var pipeline = { _: PlayerName, _: Action -> Triple(PlayerName.PLAYER1, emptyAction, Record(0,0,0)) }

    private fun appendNewActionToPipeline(action: Action, callback: (Action, Action, Outcome) -> Unit) {
        val curriedFunction = { currentPlayer: PlayerName, lastAction: Action -> processAction(currentPlayer, lastAction, action, callback) }
        this.pipeline = bind(this.pipeline, curriedFunction)
    }

    // If we're running asynchronously, this will run the pipeline to completion (for example, if the user decides to stop
    // and get the final count of wins and losses).
    private fun getResultFromPipeline(): Record {
        val res = this.pipeline(PlayerName.PLAYER2, emptyAction)
        this.pipeline = { _: PlayerName, _: Action -> res }
        return res.third
    }

    fun play() {
        // These callbacks could be asynchronous, for example, if it's updating a UI in a browser without blocking, or if Player 2 is human but remote
        val p1callback = { _: Action, a1: Action, _: Outcome -> println("You chose ${a1.name}") }
        val p2callback = { a1: Action, a2: Action, oc: Outcome -> println("Your opponent chose ${a2.name}.\nP1: ${a1.name}, P2: ${a2.name}, Outcome: ${oc.name}\n") }

        while (true) {
            val p1move = p1.getMove(true)
            if (p1move == MoveName.END_GAME) {
                val scoreboard = getResultFromPipeline()
                println("Game over. ${scoreboard.toString()}")
                return
            }
            val p1action = actionMap[p1move]

            if (p1action != null) {
                this.appendNewActionToPipeline(p1action, p1callback)
            }

            val p2move = p2.getMove(false)
            val p2action = actionMap[p2move]

            if (p2action != null) {
                this.appendNewActionToPipeline(p2action, p2callback)
            }

            // If we're doing something async, await this function so that both players' actions have been processed before Player 1 can decide to quit the game
            this.getResultFromPipeline()
        }
    }
}

// For the sake of keeping the scope simple (and readable), this is done synchronously but designed with a pipeline pattern
// so that it could be made asynchronous without requiring any locking of resources. (The technical term is a Kleisli category)
fun bind(f1: (PlayerName, Action) -> Triple<PlayerName, Action, Record>, f2: (PlayerName, Action) -> Triple<PlayerName, Action, Record>): (PlayerName, Action) -> Triple<PlayerName, Action, Record> {
    return { currentPlayer: PlayerName, lastAction: Action ->
        val res1: Triple<PlayerName, Action, Record> = f1(currentPlayer, lastAction)
        val res2: Triple<PlayerName, Action, Record> = f2(res1.first, res1.second)
        Triple(res2.first, res2.second, res1.third.increment(res2.third))
    }
}

fun processAction(currentPlayer: PlayerName, lastAction: Action, currentAction: Action, callback: (Action, Action, Outcome) -> Unit): Triple<PlayerName, Action, Record> {
    return if (currentPlayer == PlayerName.PLAYER1) {
        // callback could be made asynchronous, for example updating a UI element.
        callback(lastAction, currentAction, Outcome.WAITING)
        Triple(PlayerName.PLAYER2, currentAction, outcomeToRecord(Outcome.WAITING))
    } else {
        val outcome = lastAction.getOutcome(currentAction)
        // callback could be asynchronous, for example updating a UI element.
        callback(lastAction, currentAction, outcome)
        Triple(PlayerName.PLAYER1, currentAction, outcomeToRecord(outcome))
    }
}