package rps.project

// I've designed this to be extensible in the sense that other Actions can be added, but I'm making a simplifying assumption
// that for actions A and B, there is a tie if and only if A and B are equal. A "ties" set could be added here to cover the
// case where A is not equal to B, but A could tie B. That just increases the complexity of the getOutcome function since
// there are more error and edge cases (e.g. what if A.ties includes B but B.ties doesn't include A? What if beats and ties
// are not disjoint sets? Do we force A to necessarily be included in A.ties?)
// For the sake of code readability, I'm removing that case while still encapsulating the possibility of future changes.
class Action (val name: MoveName?, private val beats: Set<MoveName>) {
    fun getOutcome(otherAction: Action) : Outcome {
        if (this.name == otherAction.name) {
            return Outcome.TIE
        }

        if (otherAction.name in this.beats) {
            if (this.name in otherAction.beats) {
                return Outcome.UNDEFINED
            }

            return Outcome.WIN
        }

        if (this.name in otherAction.beats) {
            return Outcome.LOSS
        }

        return Outcome.UNDEFINED
    }
}
