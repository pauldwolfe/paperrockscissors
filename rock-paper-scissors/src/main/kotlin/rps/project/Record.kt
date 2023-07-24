package rps.project

// In the simple rock-paper-scissors game, every combination will lead to a well-defined outcome.
// For the sake of extensibility, if additional moves are added, we want to have an "undefined" case
// in case the rules are not well-defined. The WAITING Outcome is to simplify some logic, after one player
// has moved and is waiting for the other player.
enum class Outcome {
    WIN, LOSS, TIE, UNDEFINED, WAITING
}

class Record (private val wins: Int, private val losses: Int, private val ties: Int) {
    fun increment(by: Record): Record {
        return Record(this.wins + by.wins, this.losses + by.losses, this.ties + by.ties)
    }

    fun equals(that: Record): Boolean {
        return this.wins === that.wins && this.losses === that.losses && this.ties === that.ties
    }

    override fun toString(): String {
        return "Wins: ${this.wins}, Losses: ${this.losses}, Ties: ${this.ties}"
    }

    companion object {
        @JvmStatic
        // Encoding this way so that we can use the State monad pattern
        fun outcomeToRecord(outcome: Outcome): Record {
            return when(outcome) {
                Outcome.WIN -> Record(1, 0, 0)
                Outcome.LOSS -> Record(0, 1, 0)
                Outcome.TIE -> Record(0, 0, 1)
                else -> Record(0, 0, 0)
            }
        }
    }
}
