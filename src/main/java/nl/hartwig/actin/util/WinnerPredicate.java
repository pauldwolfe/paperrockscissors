package nl.hartwig.actin.util;

import nl.hartwig.actin.enums.PrsMove;

import java.util.function.Predicate;

public interface WinnerPredicate extends Predicate<PrsMove> {

    default boolean isWinner(PrsMove prsMove) {
        return test(prsMove);
    }

}
